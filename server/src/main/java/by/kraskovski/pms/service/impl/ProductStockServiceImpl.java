package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.repository.ProductStockRepository;
import by.kraskovski.pms.service.ProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductStockServiceImpl implements ProductStockService {

    private final ProductStockRepository productStockRepository;

    @Autowired
    public ProductStockServiceImpl(final ProductStockRepository productStockRepository) {
        this.productStockRepository = productStockRepository;
    }

    @Override
    public ProductStock find(final String id) {
        return productStockRepository.findOne(id);
    }

    @Override
    public ProductStock findByStockIdAndProductId(final String stockId, final String productId) {
        return productStockRepository.findByStockIdAndProductId(stockId, productId);
    }
}
