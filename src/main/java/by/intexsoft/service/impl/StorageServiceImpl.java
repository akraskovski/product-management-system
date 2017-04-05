package by.intexsoft.service.impl;

import by.intexsoft.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StorageServiceImpl implements StorageService {

    private Path storeLocation;
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @PostConstruct
    public void init() {
        try {
            storeLocation = Paths.get("D:/test/");
            Files.createDirectory(storeLocation);
        } catch (IOException e) {
            LOGGER.error("Storage initialisation, file already exists:", e.getMessage());
        }
    }

    @Override
    public String store(MultipartFile file) {
        String id = UUID.randomUUID().toString();
        try {
            if (file.isEmpty()) {
                LOGGER.error("Failed to store empty file " + file.getOriginalFilename());
                return null;
            }
            Files.copy(file.getInputStream(), storeLocation.resolve(id));
            return id;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e.getCause(), "storage.filesystem.file.storeFail",
                    new Object[]{file.getOriginalFilename()});
            return null;
        }
    }

    @Override
    public List<Path> loadAll() {
        try {
            return Files.walk(this.storeLocation, 1)
                    .filter(path -> !path.equals(this.storeLocation))
                    .map(this.storeLocation::relativize).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), "storage.filesystem.files.readFail", e.getCause());
        }
        return null;
    }

    @Override
    public Path load(String filename) {
        return storeLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException();
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            LOGGER.error(e.getMessage(), "storage.filesystem.file.readFail",
                    new Object[]{filename});
        }
        return null;
    }

    @Override
    public String delete(String id) {
        try {
            Files.walk(this.storeLocation, 1)
                    .filter(path -> path.toFile().getName().equals(id))
                    .findFirst().get().toFile().delete();

            return id;
        } catch (IOException | NoSuchElementException e) {
            LOGGER.error(e.getMessage(), e.getCause(), "storage.filesystem.file.deleteFail",
                    new Object[]{id});
        }
        return null;
    }
}
