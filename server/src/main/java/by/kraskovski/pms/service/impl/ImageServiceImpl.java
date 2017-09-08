package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.service.ImageService;
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
import java.nio.file.Paths;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Value("${unix.dir:#{null}}")
    private String unixDir;

    @Value("${win.dir:#{null}}")
    private String winDir;

    private static String root;

    @PostConstruct
    public void init() {
        final String os = System.getProperty("os.name");
        final String defaultDir = System.getProperty("user.home") + "/pms";

        if (os.contains("nix") || os.contains("nux") || os.indexOf("aix") > 0) {
            root = ofNullable(unixDir).orElse(defaultDir);
        } else if (os.toLowerCase().contains("win")) {
            root = ofNullable(winDir).orElse(defaultDir);
        }

        try {
            Files.createDirectory(Paths.get(root));
        } catch (IOException e) {
            log.warn("directory: \"" + root + "\" already exists!");
        }
    }

    @Override
    public String upload(final MultipartFile uploadedFile) {
        if (uploadedFile.isEmpty()) {
            return null;
        }
        final File file = new File(root + "/" + UUID.randomUUID().toString());
        try {
            uploadedFile.transferTo(file);
            return file.getName();
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Resource load(final String id) {
        final Resource resource = new FileSystemResource(root + "/" + id);
        return resource.exists() ? resource : null;
    }

    @Override
    public boolean delete(final String id) {
        final File file = new File(root + "/" + id);
        try {
            return Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public void deleteAll() {
        try {
            FileUtils.cleanDirectory(new File(root));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
