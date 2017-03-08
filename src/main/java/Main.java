import by.intexsoft.model.Product;
import by.intexsoft.model.Stock;
import by.intexsoft.model.Store;
import by.intexsoft.service.ProductService;
import by.intexsoft.service.StockService;
import by.intexsoft.service.StoreService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ProductService productService = context.getBean(ProductService.class);
        StockService stockService = context.getBean(StockService.class);
        StoreService storeService = context.getBean(StoreService.class);
    }
}
