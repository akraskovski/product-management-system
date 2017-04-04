package by.intexsoft.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface for working with files
 */
public interface StorageService {

    /**
     * Create file in the system
     */
    String store(MultipartFile file);

    /**
     * Load all files from the system
     */
    List<Path> loadAll();

    /**
     * Load one file by filename
     */
    Path load(String filename);

    Resource loadAsResource(String filename);

    /**
     * Delete one file by id
     */
    String delete(String id);
}
