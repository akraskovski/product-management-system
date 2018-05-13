package by.kraskovski.pms.domain.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Service for images
 */
public interface FileService {

    /**
     * Upload file from rest to the system
     */
    String upload(MultipartFile uploadedFile);

    /**
     * Get file as resource from system
     */
    Resource load(String id);

    /**
     * Delete file from system
     */
    void delete(String id);

    /**
     * Clean files directory
     */
    void deleteAll() throws IOException;
}
