package by.kraskovski.pms.controller;

import by.kraskovski.pms.controller.config.ControllerConfig;
import by.kraskovski.pms.domain.Product;
import by.kraskovski.pms.domain.ProductStock;
import by.kraskovski.pms.domain.Stock;
import by.kraskovski.pms.domain.enums.AuthorityEnum;
import by.kraskovski.pms.service.ProductService;
import by.kraskovski.pms.service.StockService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Map;

import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static by.kraskovski.pms.utils.TestUtils.prepareStock;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test for {@link StockController}
 */
public class StockControllerIT extends ControllerConfig {

    private static final String BASE_STOCK_URL = "/stock";

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @Before
    public void before() {
        stockService.deleteAll();
        authenticateUserWithAuthority(AuthorityEnum.ROLE_ADMIN);
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
                .andExpect(jsonPath("$.phone", is(stock.getPhone())))
                .andExpect(jsonPath("$.address", is(stock.getAddress())));
    }

    @Test
    public void findStockByIdTest() throws Exception {
        final Stock stock = prepareStock();
        stockService.create(stock);

        mvc.perform(get(BASE_STOCK_URL + "/" + stock.getId())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.specialize", is(stock.getSpecialize())))
                .andExpect(jsonPath("$.phone", is(stock.getPhone())))
                .andExpect(jsonPath("$.address", is(stock.getAddress())));
    }

    @Test
    public void addNewProductToStockTest() throws Exception {
        final Product product = prepareProduct();
        final Stock stock = prepareStock();
        productService.create(product);
        stockService.create(stock);

        mvc.perform(put(BASE_STOCK_URL + "/product")
                .header(authHeaderName, token)
                .param("stock_id", stock.getId())
                .param("product_id", product.getId())
                .param("count", "10"))
                .andExpect(status().isNoContent());

        final String result = mvc.perform(get(BASE_STOCK_URL + "/" + stock.getId() + "/products")
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        final Map<String, Integer> productsInStock = objectMapper.readValue(result, new TypeReference<Map<String, Integer>>() {
        });

        Assert.assertTrue(productsInStock.containsKey(product.getId()));
    }

    @Test
    public void deleteProductFromStockPositiveTest() throws Exception {
        final int productsInStockCount = 10,
                productsToDeleteCount = 2;
        final Product product = prepareProduct();
        productService.create(product);
        final Stock stock = prepareStock();
        stock.getProductStocks().add(new ProductStock(product, stock, productsInStockCount));
        stockService.create(stock);

        mvc.perform(delete(BASE_STOCK_URL + "/product")
                .header(authHeaderName, token)
                .param("stock_id", stock.getId())
                .param("product_id", product.getId())
                .param("count", String.valueOf(productsToDeleteCount)))
                .andExpect(status().isNoContent());

        final String result = mvc.perform(get(BASE_STOCK_URL + "/" + stock.getId() + "/products")
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        final Map<String, Integer> productsInStock = objectMapper.readValue(result, new TypeReference<Map<String, Integer>>() {
        });

        Assert.assertTrue(productsInStock.containsKey(product.getId()));
        Assert.assertTrue(productsInStock.get(product.getId()).equals(productsInStockCount - productsToDeleteCount));
    }

    @Test
    public void deleteProductFromStockNegativeTest() throws Exception {
        final int productsToDeleteCount = 10;
        final Stock stock = prepareStock();
        stockService.create(stock);

        mvc.perform(delete(BASE_STOCK_URL + "/product")
                .header(authHeaderName, token)
                .param("stock_id", stock.getId())
                .param("product_id", "INVALID ID")
                .param("count", String.valueOf(productsToDeleteCount)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateStockTest() throws Exception {
        final Stock stock = prepareStock();
        stockService.create(stock);

        stock.setSpecialize(random(20));
        stock.setPhone(random(20));
        stock.setAddress(random(20));

        mvc.perform(put(BASE_STOCK_URL)
                .header(authHeaderName, token)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(stock)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.specialize", is(stock.getSpecialize())))
                .andExpect(jsonPath("$.phone", is(stock.getPhone())))
                .andExpect(jsonPath("$.address", is(stock.getAddress())));
    }

    @Test
    public void deleteStockTest() throws Exception {
        final Stock stock = prepareStock();
        stockService.create(stock);

        mvc.perform(delete(BASE_STOCK_URL + "/" + stock.getId())
                .header(authHeaderName, token))
                .andExpect(status().isNoContent());
    }
}
