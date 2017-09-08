package by.kraskovski.pms.controller;

import by.kraskovski.pms.controller.config.ControllerConfig;
import by.kraskovski.pms.service.ImageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static by.kraskovski.pms.domain.enums.AuthorityEnum.ROLE_ADMIN;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ImageControllerTest extends ControllerConfig {

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

        mvc.perform(MockMvcRequestBuilders.fileUpload(BASE_IMAGE_URL + "/upload")
                .file(image)
                .header(authHeaderName, token)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }


    @Test
    public void uploadImageNegativeTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.fileUpload(BASE_IMAGE_URL + "/upload")
                .header(authHeaderName, token)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loadImageAsResource() throws Exception {
    }

    @Test
    public void deleteImage() throws Exception {
    }

}