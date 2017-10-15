package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.config.ControllerTestConfig;
import by.kraskovski.pms.application.controller.dto.StoreDto;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.domain.model.Store;
import by.kraskovski.pms.domain.service.StockService;
import by.kraskovski.pms.domain.service.StoreService;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static by.kraskovski.pms.utils.TestUtils.prepareStock;
import static by.kraskovski.pms.utils.TestUtils.prepareStore;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StoreControllerIT extends ControllerTestConfig {

    private static final String BASE_STORE_URL = "/store";

    @Autowired
    private StoreService storeService;

    @Autowired
    private StockService stockService;

    @Autowired
    private Mapper mapper;

    @Before
    public void before() {
        storeService.deleteAll();
        stockService.deleteAll();
        authenticateUserWithAuthority(AuthorityEnum.ROLE_ADMIN);
    }

    @After
    public void after() {
        cleanup();
        storeService.deleteAll();
        stockService.deleteAll();
    }

    @Test
    public void createStorePositiveTest() throws Exception {
        final Store store = prepareStore();
        mvc.perform(post(BASE_STORE_URL)
                .header(authHeaderName, token)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(mapper.map(store, StoreDto.class))))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void createStoreNegativeTest() throws Exception {
        final Store store = prepareStore();
        store.setName(null);
        mvc.perform(post(BASE_STORE_URL)
                .header(authHeaderName, token)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(mapper.map(store, StoreDto.class))))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void loadStoreByIdTest() throws Exception {
        final Store store = storeService.create(prepareStore());
        mvc.perform(get(BASE_STORE_URL + "/" + store.getId())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(store.getId())));
    }

    @Test
    public void loadStoreStocksByIdIfExistsTest() throws Exception {
        final Store store = prepareStore();
        final Stock stock = stockService.create(prepareStock());
        store.getStockList().add(stock);
        storeService.create(store);
        mvc.perform(get(BASE_STORE_URL + "/" + store.getId() + "/stock-manage")
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(stock.getId())));
    }

    @Test
    public void loadStoreStocksByIdIfNotExistsTest() throws Exception {
        final Store store = storeService.create(prepareStore());
        mvc.perform(get(BASE_STORE_URL + "/" + store.getId() + "/stock-manage")
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void addStockToStorePositiveTest() throws Exception {
        final Store store = storeService.create(prepareStore());
        final Stock stock = stockService.create(prepareStock());
        mvc.perform(put(BASE_STORE_URL + "/stock-manage")
                .header(authHeaderName, token)
                .param("storeId", store.getId())
                .param("stockId", stock.getId()))
                .andExpect(status().isNoContent());
        mvc.perform(get(BASE_STORE_URL + "/" + store.getId() + "/stock-manage")
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(stock.getId())));
    }

    @Test
    public void addStockToStoreIfAlreadyExistsTest() throws Exception {
        final Store store = prepareStore();
        final Stock stock = stockService.create(prepareStock());
        store.getStockList().add(stock);
        storeService.create(store);
        mvc.perform(put(BASE_STORE_URL + "/stock-manage")
                .header(authHeaderName, token)
                .param("storeId", store.getId())
                .param("stockId", stock.getId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteStockFromStorePositiveTest() throws Exception {
        final Store store = prepareStore();
        final Stock stock = stockService.create(prepareStock());
        store.getStockList().add(stock);
        storeService.create(store);
        mvc.perform(delete(BASE_STORE_URL + "/stock-manage")
                .header(authHeaderName, token)
                .param("storeId", store.getId())
                .param("stockId", stock.getId()))
                .andExpect(status().isNoContent());
        mvc.perform(get(BASE_STORE_URL + "/" + store.getId() + "/stock-manage")
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void deleteStockFromStoreNegativeTest() throws Exception {
        final Store store = storeService.create(prepareStore());
        mvc.perform(delete(BASE_STORE_URL + "/stock-manage")
                .header(authHeaderName, token)
                .param("storeId", store.getId())
                .param("stockId", randomAlphabetic(20)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateStorePositiveTest() throws Exception {
        final Store store = storeService.create(prepareStore());
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
                .content(objectMapper.writeValueAsString(mapper.map(store, StoreDto.class))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(store.getId())))
                .andExpect(jsonPath("$.name", is(store.getName())))
                .andExpect(jsonPath("$.address", is(store.getAddress())))
                .andExpect(jsonPath("$.details", is(store.getDetails())))
                .andExpect(jsonPath("$.discounts", is(store.isDiscounts())))
                .andExpect(jsonPath("$.logo", is(store.getLogo())))
                .andExpect(jsonPath("$.mail", is(store.getMail())))
                .andExpect(jsonPath("$.phone", is(store.getPhone())))
                .andExpect(jsonPath("$.skype", is(store.getSkype())));
    }

    @Test
    public void updateStoreNegativeTest() throws Exception {
        final Store store = storeService.create(prepareStore());
        store.setName(null);
        mvc.perform(put(BASE_STORE_URL)
                .header(authHeaderName, token)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(mapper.map(store, StoreDto.class))))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void deleteStoreTest() throws Exception {
        final Store store = storeService.create(prepareStore());
        mvc.perform(delete(BASE_STORE_URL + "/" + store.getId())
                .header(authHeaderName, token))
                .andExpect(status().isNoContent());
    }
}