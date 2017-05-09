package by.kraskovski;

import by.kraskovski.service.ImageService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @After
    public void deleteAll() {
        imageService.deleteAll();
    }

    @Test
    public void uploadImage() {
        MultipartFile file = new MockMultipartFile("image", "image.jpg", "image/png", "test".getBytes());
        String id = imageService.upload(file);
        try {
            Assert.assertEquals(imageService.load(id).getFile().getName(), id);
            Assert.assertEquals(imageService.load(id).getFile().length(), file.getSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadImage() {
        MultipartFile file = new MockMultipartFile("file", "file.gif", "image/png", "test".getBytes());
        String id = imageService.upload(file);
        try {
            Assert.assertEquals(imageService.load(id).getFilename(), id);
            Assert.assertEquals(imageService.load(id).contentLength(), file.getSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteImage() {
        MultipartFile file = new MockMultipartFile("file", "file.gif", "image/png", "test".getBytes());
        String id = imageService.upload(file);
        try {
            Assert.assertEquals(imageService.load(id).getFilename(), id);
            Assert.assertEquals(imageService.load(id).contentLength(), file.getSize());
            imageService.delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
