package by.kraskovski.pms.audit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Service for logging Spring AOP Advices.
 */
@Slf4j
@Component
public class LoggingAuditor implements Auditing {

    @Override
    public void logService(final String serviceName, final String methodName,
                           final Object[] args) {
        validateServiceName(serviceName);
        validateMethodName(methodName);

        log.info("Message : Called service: {} for method: {} with arguments: {}",
                serviceName, methodName, args);
    }

    @Override
    public void logException(final String serviceName, final Throwable exception) {
        validateServiceName(serviceName);

        log.info("Message : Thrown exception: '{}' in service: {}",
                exception, serviceName);
    }

    private void validateMethodName(final String methodName) {
        if (StringUtils.isBlank(methodName)) {
            throw new IllegalArgumentException("Method is mandatory");
        }
    }

    private void validateServiceName(final String serviceName) {
        if (StringUtils.isBlank(serviceName)) {
            throw new IllegalArgumentException("Service name is mandatory");
        }
    }
}
