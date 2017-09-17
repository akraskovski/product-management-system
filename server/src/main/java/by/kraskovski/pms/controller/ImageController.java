package by.kraskovski.pms.controller;

import by.kraskovski.pms.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Controller for {@link ImageService}.
 */
@RestController
@RequestMapping("/image")
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(final ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Uploading image to the system.
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity uploadImage(@RequestParam("file") final MultipartFile uploadedFile) throws IOException {
        log.info("uploading image: \"" + uploadedFile.getOriginalFilename() + "\"");
        final String image = imageService.upload(uploadedFile);
        if (isNotEmpty(image)) {
            return new ResponseEntity<>(image, HttpStatus.CREATED);
        }
        log.error("Error during uploading image: \"" + uploadedFile.getOriginalFilename() + "\"");
        return new ResponseEntity<>(
                "Error during uploading image: \"" + uploadedFile.getOriginalFilename() + "\"",
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Get image from the system.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"image/jpeg", "image/jpg", "image/png", "image/gif"})
    @ResponseBody
    public ResponseEntity loadImageAsResource(@PathVariable final String id) {
        log.info("loading image with id: \"" + id + "\"");
        final Resource resource = imageService.load(id);
        if (resource != null) {
            return ResponseEntity.ok(resource);
        }
        log.error("Error during loading image with id: \"" + id + "\"");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Delete image from system.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteImage(@PathVariable final String id) {
        log.info("deleting image with id: \"" + id + "\"");
        if (imageService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.error("Error during deleting image with id: \"" + id + "\"");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
