package by.kraskovski.pms.domain.service.stock;

import by.kraskovski.pms.domain.repository.StockRepository;
import by.kraskovski.pms.domain.service.ProductService;
import by.kraskovski.pms.domain.service.ProductStockService;
import by.kraskovski.pms.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service management products in the warehouse
 * for the warehouse manager ot administrator.
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StockManagementService {

    private final StockRepository stockRepository;
    private final ProductService productService;
    private final ProductStockService productStockService;
    private final UserService userService;

}
