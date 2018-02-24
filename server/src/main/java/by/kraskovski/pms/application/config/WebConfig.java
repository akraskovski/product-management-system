package by.kraskovski.pms.application.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * Web configuration for the application.
 */
@Configuration
public class WebConfig {

    /**
     * Creates configured jackson builder bean.
     *
     * @param dateFormat date format
     * @return created jackson builder
     */
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder(final DateFormat dateFormat) {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true).dateFormat(dateFormat);
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.failOnUnknownProperties(false);
        return builder;
    }

    /**
     * Created date format bean.
     *
     * @return created date format
     */
    @Bean
    public DateFormat dateFormat() {
        final DateFormat result = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        result.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        return result;
    }
}
