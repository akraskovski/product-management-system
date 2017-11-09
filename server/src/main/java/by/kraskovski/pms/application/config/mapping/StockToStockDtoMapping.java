package by.kraskovski.pms.application.config.mapping;

import by.kraskovski.pms.application.controller.dto.StockDto;
import by.kraskovski.pms.domain.model.Stock;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

/**
 * Custom dozer mapping file for {@link StockDto}.
 */
public class StockToStockDtoMapping extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(Stock.class, StockDto.class, TypeMappingOptions.oneWay())
                .fields("manager.id", "managerId");
    }
}
