package by.kraskovski.pms.audit;

/**
 * General cross-count auditing logic interface.
 */
public interface Auditing {

    /**
     * Logging service execution.
     *
     * @param serviceName service class name
     * @param methodName  service executions method
     * @param args        method arguments
     */
    void logService(String serviceName, String methodName, Object[] args);

    /**
     * Logging any thrown exception.
     *
     * @param serviceName service class name
     * @param exception   exception object
     */
    void logException(String serviceName, Throwable exception);
}
