package by.kraskovski.pms;

import by.kraskovski.pms.model.Product;
import by.kraskovski.pms.model.ProductStock;
import by.kraskovski.pms.model.Stock;
import by.kraskovski.pms.repository.ProductRepository;
import by.kraskovski.pms.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public abstract class Application implements CommandLineRunner{

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    @Autowired
    public Application(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product product = new Product();
        product.setName("Mobile");

        Stock stock = new Stock();
        stock.setSpecialize("Phones");
        //stockRepository.save(stock);

        ProductStock productStock = new ProductStock();
        productStock.setProduct(product);
        productStock.setStock(stock);
        productStock.setProductsCount(10);
        product.getProductStocks().add(productStock);
        productRepository.save(product);
    }
}
