package by.kraskovski.pms.application.config.converter;

import by.kraskovski.pms.application.controller.dto.StockDto;
import by.kraskovski.pms.domain.model.Stock;
import org.dozer.CustomConverter;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class CustomDozerConverter implements CustomConverter {

    @Override
    public Object convert(Object destinationValue, Object sourceValue,
                          final Class<?> destinationClass, final Class<?> sourceClass) {
        if (destinationClass == null || sourceClass == null) {
            return destinationValue;
        }

        if (sourceValue == null) {
            destinationValue = null;
        } else if (destinationClass.isAssignableFrom(StockDto.class) && sourceClass.isAssignableFrom(Stock.class)) {
            final Stock source = (Stock) sourceValue;
            final StockDto result = new StockDto();
            result.setManagerId(source.getManager().getId());
            destinationValue = result;
        } else if (destinationClass.isAssignableFrom(LocalTime.class) && sourceClass.isAssignableFrom(LocalTime.class)) {
            final LocalTime source = (LocalTime) sourceValue;
            destinationValue = LocalTime.of(source.getHour(), source.getMinute(), source.getSecond(), source.getNano());
        } else if (destinationClass.isAssignableFrom(LocalDateTime.class) && sourceClass.isAssignableFrom(LocalDateTime.class)) {
            final LocalDateTime source = (LocalDateTime) sourceValue;
            destinationValue = LocalDateTime.of(
                    source.getYear(),
                    source.getMonth(),
                    source.getDayOfMonth(),
                    source.getHour(),
                    source.getMinute(),
                    source.getSecond(),
                    source.getNano()
            );
        }
        return destinationValue;
    }
}
