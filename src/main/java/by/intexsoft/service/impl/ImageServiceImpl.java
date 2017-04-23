package by.intexsoft.service.impl;

import by.intexsoft.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private static final String uploadingDir = "D:/pms/";

    @Override
    public String upload(MultipartFile uploadedFile) {
        File file = new File(uploadingDir + uploadedFile.getOriginalFilename());
        try {
            uploadedFile.transferTo(file);
            return file.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
