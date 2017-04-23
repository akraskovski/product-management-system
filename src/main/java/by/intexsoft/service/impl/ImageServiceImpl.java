package by.intexsoft.service.impl;

import by.intexsoft.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
    private static final String uploadingDir = "D:/pms/";

    @PostConstruct
    public void init() {
        try {
            Files.createDirectory(Paths.get(uploadingDir));
        } catch (IOException e) {
            LOGGER.error("directory: \"" + uploadingDir + "\" already exists!");
        }
    }

    @Override
    public String upload(MultipartFile uploadedFile) {
        if (uploadedFile.isEmpty())
            return null;
        File file = new File(uploadingDir + UUID.randomUUID().toString());
        try {
            uploadedFile.transferTo(file);
            return file.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Resource load(String id) {
        Resource resource = new FileSystemResource(uploadingDir + id);
        return resource.exists() ? resource : null;
    }
}
