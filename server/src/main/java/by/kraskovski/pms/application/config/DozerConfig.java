package by.kraskovski.pms.application.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Configuration class for Dozer.
 */
@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper mapper() {
        final DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(Arrays.asList("dozer-custom-mapping.xml", "dozerJdk8Converters.xml"));
        return dozerBeanMapper;
    }
}
