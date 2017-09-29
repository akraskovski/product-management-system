package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.config.ControllerTestConfig;
import by.kraskovski.pms.domain.service.ImageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static by.kraskovski.pms.domain.model.enums.AuthorityEnum.ROLE_ADMIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ImageControllerIT extends ControllerTestConfig {

    private static final String BASE_IMAGE_URL = "/image";

    @Autowired
    private ImageService imageService;

    @Before
    public void before() {
        imageService.deleteAll();
        authenticateUserWithAuthority(ROLE_ADMIN);
    }

    @After
    public void after() {
        imageService.deleteAll();
        cleanup();
    }

    @Test
    public void uploadImagePositiveTest() throws Exception {
        final MockMultipartFile image = new MockMultipartFile("file", "content".getBytes());

        mvc.perform(fileUpload(BASE_IMAGE_URL + "/upload")
                .file(image)
                .header(authHeaderName, token)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void uploadImageNegativeTest() throws Exception {
        mvc.perform(fileUpload(BASE_IMAGE_URL + "/upload")
                .header(authHeaderName, token)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loadImageAsResourcePositiveTest() throws Exception {
        final MockMultipartFile image = new MockMultipartFile("file", "image.jpg", null, "content".getBytes());
        final String imageId = imageService.upload(image);

        mvc.perform(get(BASE_IMAGE_URL + "/" + imageId)
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().bytes(image.getBytes()))
                .andExpect(content().contentType(MediaType.IMAGE_JPEG));
    }

    @Test
    public void loadImageAsResourceNegativeTest() throws Exception {
        mvc.perform(get(BASE_IMAGE_URL + "/" + RandomStringUtils.randomAlphabetic(20))
                .header(authHeaderName, token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteImagePositiveTest() throws Exception {
        final MockMultipartFile image = new MockMultipartFile("file", "content".getBytes());
        final String imageId = imageService.upload(image);

        mvc.perform(delete(BASE_IMAGE_URL + "/" + imageId)
                .header(authHeaderName, token))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteImageNegativeTest() throws Exception {
        mvc.perform(delete(BASE_IMAGE_URL + "/" + RandomStringUtils.randomAlphabetic(20))
                .header(authHeaderName, token))
                .andExpect(status().isNotFound());
    }
}