package by.kraskovski.pms.domain.audit;

import by.kraskovski.pms.audit.LoggingAuditor;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Spring AOP {@link Aspect} for service auditing,
 */
@Aspect
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoggingAspect {

    private final LoggingAuditor auditor;

    @Pointcut("execution(* by.kraskovski.pms.domain.service.*.*(..)))")
    public void service() {
        // do nothing
    }

    @Before("by.kraskovski.pms.domain.audit.LoggingAspect.service()")
    public void logService(final JoinPoint joinPoint) {
        auditor.logService(joinPoint.getTarget().toString(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @AfterThrowing(pointcut = "by.kraskovski.pms.domain.audit.LoggingAspect.service()", throwing = "exception")
    public void logException(final JoinPoint joinPoint, final Throwable exception) {
        auditor.logException(joinPoint.getTarget().toString(), exception);
    }
}
