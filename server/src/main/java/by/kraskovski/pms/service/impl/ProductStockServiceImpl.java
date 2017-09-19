package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.repository.ProductStockRepository;
import by.kraskovski.pms.service.ProductStockService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ProductStockServiceImpl implements ProductStockService {

    private final ProductStockRepository productStockRepository;

    @Override
    public ProductStock find(final String id) {
        return Optional.ofNullable(productStockRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("ProductStock with id: " + id + " not found in db!"));
    }

    @Override
    public ProductStock findByStockIdAndProductId(final String stockId, final String productId) {
        return Optional.ofNullable(productStockRepository.findByStockIdAndProductId(stockId, productId))
                .orElseThrow(() -> new EntityNotFoundException(
                        "ProductStock with stockId: " + stockId + ", ProductId: " + productId + " not found in db!"));
    }
}
