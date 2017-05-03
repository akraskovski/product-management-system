package by.kraskovski.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service for images
 */
public interface ImageService {

    /**
     * Upload file from rest to the system
     */
    String upload(MultipartFile uploadedFile);

    /**
     * Get image as resource from system
     */
    Resource load(String id);

    /**
     * Delete image from system
     */
    boolean delete(String id);
}
