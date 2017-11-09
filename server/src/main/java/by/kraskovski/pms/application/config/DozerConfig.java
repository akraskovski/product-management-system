package by.kraskovski.pms.application.config;

import by.kraskovski.pms.application.config.mapping.StockToStockDtoMapping;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * Configuration class for Dozer.
 */
@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper mapper() {
        final DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
        dozerBeanMapper.addMapping(new StockToStockDtoMapping());
        return dozerBeanMapper;
    }
}
