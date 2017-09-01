package by.kraskovski.pms.controller;

import by.kraskovski.pms.controller.config.ControllerConfig;
import by.kraskovski.pms.domain.Store;
import by.kraskovski.pms.domain.enums.AuthorityEnum;
import by.kraskovski.pms.service.StoreService;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static by.kraskovski.pms.utils.TestUtils.*;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StoreControllerIT extends ControllerConfig {

    private static final String BASE_STORE_URL = "/store";

    @Autowired
    private StoreService storeService;

    @Before
    public void before() {
        storeService.deleteAll();
        authenticateUserWithAuthority(AuthorityEnum.ROLE_ADMIN);
    }

    @After
    public void after() {
        cleanup();
        storeService.deleteAll();
    }

    @Test
    public void createStore() throws Exception {
        final Store store = prepareStore();

        mvc.perform(post(BASE_STORE_URL)
                .header(authHeaderName, token)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(store)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(store.getName())))
                .andExpect(jsonPath("$.address", is(store.getAddress())))
                .andExpect(jsonPath("$.details", is(store.getDetails())))
                .andExpect(jsonPath("$.discounts", is(store.getDiscounts())))
                .andExpect(jsonPath("$.logo", is(store.getLogo())))
                .andExpect(jsonPath("$.mail", is(store.getMail())))
                .andExpect(jsonPath("$.phone", is(store.getPhone())))
                .andExpect(jsonPath("$.skype", is(store.getSkype())));
    }

    @Test
    public void loadStoreById() throws Exception {
        final Store store = prepareStore();
        storeService.create(store);

        mvc.perform(get(BASE_STORE_URL + "/" + store.getId())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(store.getId())))
                .andExpect(jsonPath("$.name", is(store.getName())))
                .andExpect(jsonPath("$.address", is(store.getAddress())))
                .andExpect(jsonPath("$.details", is(store.getDetails())))
                .andExpect(jsonPath("$.discounts", is(store.getDiscounts())))
                .andExpect(jsonPath("$.logo", is(store.getLogo())))
                .andExpect(jsonPath("$.mail", is(store.getMail())))
                .andExpect(jsonPath("$.phone", is(store.getPhone())))
                .andExpect(jsonPath("$.skype", is(store.getSkype())));
    }

    @Test
    public void updateStore() throws Exception {
        final Store store = prepareStore();
        storeService.create(store);

        store.setName(random(20));
        store.setAddress(random(20));
        store.setDetails(random(20));
        store.setDiscounts(false);
        store.setLogo(random(20));
        store.setMail(random(20));
        store.setPhone(random(20));
        store.setSkype(random(20));

        mvc.perform(put(BASE_STORE_URL)
                .header(authHeaderName, token)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(store)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(store.getId())))
                .andExpect(jsonPath("$.name", is(store.getName())))
                .andExpect(jsonPath("$.address", is(store.getAddress())))
                .andExpect(jsonPath("$.details", is(store.getDetails())))
                .andExpect(jsonPath("$.discounts", is(store.getDiscounts())))
                .andExpect(jsonPath("$.logo", is(store.getLogo())))
                .andExpect(jsonPath("$.mail", is(store.getMail())))
                .andExpect(jsonPath("$.phone", is(store.getPhone())))
                .andExpect(jsonPath("$.skype", is(store.getSkype())));
    }

    @Test
    public void deleteStore() throws Exception {
        final Store store = prepareStore();
        store.setLogo(StringUtils.EMPTY);
        storeService.create(store);

        mvc.perform(delete(BASE_STORE_URL + "/" + store.getId())
                .header(authHeaderName, token))
                .andExpect(status().isNoContent());
    }
}