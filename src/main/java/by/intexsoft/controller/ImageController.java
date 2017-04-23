package by.intexsoft.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Value("uploadingDir")
    private String uploadingDir;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getImage(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile uploadedFile) throws IOException {
        File file = new File(uploadingDir + uploadedFile.getOriginalFilename());
        uploadedFile.transferTo(file);
        System.out.println("Absolut path: " + file.getAbsolutePath());
        System.out.println(file.exists());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteImage(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
