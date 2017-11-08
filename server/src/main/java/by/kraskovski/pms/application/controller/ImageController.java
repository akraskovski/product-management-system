package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.domain.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for {@link FileService}.
 */
@RestController
@RequestMapping("/image")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ImageController {

    private final FileService imageService;

    /**
     * Uploading image to the system.
     */
    @PostMapping("/upload")
    public ResponseEntity uploadImage(@RequestParam("file") final MultipartFile uploadedFile) {
        log.info("uploading image: \"" + uploadedFile.getOriginalFilename() + "\"");
        return new ResponseEntity<>(imageService.upload(uploadedFile), HttpStatus.CREATED);
    }

    /**
     * Get image from the system.
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity loadImageAsResource(@PathVariable final String id) {
        log.info("loading image with id: \"{}\"", id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageService.load(id));
    }

    /**
     * Delete image from system.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable final String id) {
        log.info("deleting image with id: \"" + id + "\"");
        imageService.delete(id);
    }
}
