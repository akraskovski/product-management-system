package by.kraskovski.pms.controller;

import by.kraskovski.pms.domain.Stock;
import by.kraskovski.pms.domain.enums.AuthorityEnum;
import by.kraskovski.pms.service.StockService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static by.kraskovski.pms.utils.TestUtils.prepareStock;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StockControllerIT extends ControllerConfig {

    private static final String BASE_STOCK_URL = "/stock";

    @Autowired
    private StockService stockService;

    @Before
    public void before() {
        stockService.deleteAll();
        setup(AuthorityEnum.ROLE_ADMIN);
    }

    @After
    public void after() {
        cleanup();
        stockService.deleteAll();
    }

    @Test
    public void createStockTest() throws Exception {
        final Stock stock = prepareStock();
        mvc.perform(post(BASE_STOCK_URL)
                .header(authHeaderName, token)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(stock)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.specialize", is(stock.getSpecialize())))
                .andExpect(jsonPath("$.address", is(stock.getSpecialize())));
    }
}
