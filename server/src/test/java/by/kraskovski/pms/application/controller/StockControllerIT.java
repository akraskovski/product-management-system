package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.config.ControllerConfig;
import by.kraskovski.pms.application.controller.dto.ProductStockDto;
import by.kraskovski.pms.application.controller.dto.StockDto;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.domain.service.ProductService;
import by.kraskovski.pms.domain.service.StockService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static by.kraskovski.pms.utils.TestUtils.prepareStock;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for {@link StockController}
 */
public class StockControllerIT extends ControllerConfig {

    private static final String BASE_STOCK_URL = "/stock";

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @Autowired
    private Mapper mapper;

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
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void findStockByIdTest() throws Exception {
        final Stock stock = prepareStock();
        stockService.create(stock);
        mvc.perform(get(BASE_STOCK_URL + "/" + stock.getId())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(stock.getId())));
    }

    @Test
    public void addExistingProductToStockTest() throws Exception {
        final Product product = productService.create(prepareProduct());
        final Stock stock = prepareStock();
        stock.getProductStocks().add(new ProductStock(product, stock, 10));
        stockService.create(stock);
        mvc.perform(put(BASE_STOCK_URL + "/product")
                .header(authHeaderName, token)
                .param("stock_id", stock.getId())
                .param("product_id", product.getId())
                .param("count", "10"))
                .andExpect(status().isNoContent());
        final ProductStockDto productStockDto = loadStockProducts(stock.getId()).get(0);
        assertEquals(product.getId(), productStockDto.getProduct().getId());
        assertEquals(20, productStockDto.getProductsCount());
    }

    @Test
    public void addNewProductToStockTest() throws Exception {
        final Product product = productService.create(prepareProduct());
        final Stock stock = stockService.create(prepareStock());
        mvc.perform(put(BASE_STOCK_URL + "/product")
                .header(authHeaderName, token)
                .param("stock_id", stock.getId())
                .param("product_id", product.getId())
                .param("count", "10"))
                .andExpect(status().isNoContent());
        final ProductStockDto productStockDto = loadStockProducts(stock.getId()).get(0);
        assertEquals(product.getId(), productStockDto.getProduct().getId());
        assertEquals(10, productStockDto.getProductsCount());
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
        final ProductStockDto productStockDtos = loadStockProducts(stock.getId()).get(0);
        assertEquals(product.getId(), productStockDtos.getProduct().getId());
        assertEquals(productsInStockCount - productsToDeleteCount, productStockDtos.getProductsCount());
    }

    @Test
    public void deleteProductFromStockNegativeTest() throws Exception {
        final int productsToDeleteCount = 10;
        final Stock stock = stockService.create(prepareStock());
        mvc.perform(delete(BASE_STOCK_URL + "/product")
                .header(authHeaderName, token)
                .param("stock_id", stock.getId())
                .param("product_id", "INVALID ID")
                .param("count", String.valueOf(productsToDeleteCount)))
                .andExpect(status().isNotFound());
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
                .content(objectMapper.writeValueAsString(mapper.map(stock, StockDto.class))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(stock.getId())))
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

    private List<ProductStockDto> loadStockProducts(final String stockId) throws Exception {
        final String response = mvc.perform(get(BASE_STOCK_URL + "/" + stockId + "/products")
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(response, new TypeReference<List<ProductStockDto>>() {
        });
    }
}
