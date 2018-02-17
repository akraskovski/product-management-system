package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.service.exception.FileNotFoundException;
import by.kraskovski.pms.domain.service.exception.FileUploadException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Service
@Slf4j
public class ImageService implements FileService {

    @Value("${unix.dir:#{null}}")
    private String unixDir;

    @Value("${win.dir:#{null}}")
    private String winDir;

    private static String root;

    @PostConstruct
    public void init() throws IOException {
        final String os = System.getProperty("os.name");
        final String defaultDir = System.getProperty("user.home") + "/pms";

        if (os.contains("nix") || os.contains("nux") || os.indexOf("aix") > 0) {
            root = ofNullable(unixDir).orElse(defaultDir);
        } else if (os.toLowerCase().contains("win")) {
            root = ofNullable(winDir).orElse(defaultDir);
        }

        final Path directoryPath = Paths.get(root);
        if (!Files.exists(directoryPath)) {
            Files.createDirectory(Paths.get(root));
        }
    }

    @Override
    public String upload(final MultipartFile uploadedFile) {
        if (uploadedFile.isEmpty()) {
            throw new FileUploadException("Error during upload image. Uploading file is empty!");
        }
        final File file = new File(root + "/" + UUID.randomUUID().toString());
        try {
            uploadedFile.transferTo(file);
            return file.getName();
        } catch (IOException e) {
            throw new FileUploadException(e.getLocalizedMessage());
        }
    }

    @Override
    public Resource load(final String id) {
        final Resource resource = new FileSystemResource(root + "/" + id);
        if (!resource.exists()) {
            throw new FileNotFoundException("File: " + id + " not found in system!");
        }
        return resource;
    }

    @Override
    public void delete(final String id) {
        final File file = new File(root + "/" + id);
        try {
            if (!Files.deleteIfExists(file.toPath())) {
                throw new NoSuchFileException("File: " + id + " not found in system!");
            }
        } catch (IOException e) {
            throw new FileNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteAll() throws IOException {
        FileUtils.cleanDirectory(new File(root));
    }
}
