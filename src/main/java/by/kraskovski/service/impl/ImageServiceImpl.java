package by.kraskovski.service.impl;

import by.kraskovski.service.ImageService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${unix.dir}")
    private String UNIX_DIR;
    @Value("${win.dir}")
    private String WINDOWS_DIR;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
    private static String root;

    @PostConstruct
    public void init() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("nix") || os.contains("nux") || os.indexOf("aix") > 0)
                root = UNIX_DIR;
            else if (os.contains("win"))
                root = "D:\\pms";
            Files.createDirectory(Paths.get(root));
        } catch (IOException e) {
            LOGGER.error("directory: \"" + root + "\" already exists!");
        }
    }

    @Override
    public String upload(MultipartFile uploadedFile) {
        if (uploadedFile.isEmpty())
            return null;
        File file = new File(root + "/" + UUID.randomUUID().toString());
        try {
            uploadedFile.transferTo(file);
            return file.getName();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    @Override
    public Resource load(String id) {
        Resource resource = new FileSystemResource(root + "/" + id);
        return resource.exists() ? resource : null;
    }

    @Override
    public boolean delete(String id) {
        File file = new File(root + id);
        try {
            return Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteAll() {
        try {
            FileUtils.cleanDirectory(new File(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
