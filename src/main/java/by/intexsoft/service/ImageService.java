package by.intexsoft.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String upload (MultipartFile uploadedFile);
}
