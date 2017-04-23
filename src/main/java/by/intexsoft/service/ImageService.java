package by.intexsoft.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String upload(MultipartFile uploadedFile);

    Resource load(String id);
}
