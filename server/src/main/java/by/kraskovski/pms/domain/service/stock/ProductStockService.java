package by.kraskovski.pms.domain.service.stock;

import by.kraskovski.pms.domain.model.ProductStock;

/**
 * Service for {@link ProductStock}
 */
public interface ProductStockService {

    /**
     * Find {@link ProductStock} by id
     */
    ProductStock find(String id);

    /**
     * Find {@link ProductStock} by stock and products ids
     */
    ProductStock findByStockIdAndProductId(String stockId, String productId);
}
