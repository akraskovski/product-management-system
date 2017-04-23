package by.intexsoft.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getImage(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteImage(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
