package by.kraskovski.controller;

import by.kraskovski.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@RestController
@RequestMapping("/image")
public class ImageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Uploading image to the system
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile uploadedFile) throws IOException {
        LOGGER.info("uploading image: \"" + uploadedFile.getOriginalFilename() + "\"");
        String image = imageService.upload(uploadedFile);
        if (isNotEmpty(image))
            return new ResponseEntity<>(image, HttpStatus.CREATED);
        LOGGER.error("Error during uploading image: \"" + uploadedFile.getOriginalFilename() + "\"");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Get image from the system
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"image/jpeg", "image/jpg", "image/png", "image/gif"})
    @ResponseBody
    public ResponseEntity loadImageAsResource(@PathVariable String id) {
        LOGGER.info("loading image with id: \"" + id + "\"");
        Resource resource = imageService.load(id);
        if (resource != null)
            return new ResponseEntity<>(resource, HttpStatus.OK);
        LOGGER.error("Error during loading image with id: \"" + id + "\"");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Delete image from system
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteImage(@PathVariable String id) {
        LOGGER.info("deleting image with id: \"" + id + "\"");
        if (imageService.delete(id))
            return new ResponseEntity<>(HttpStatus.OK);
        LOGGER.error("Error during deleting image with id: \"" + id + "\"");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}