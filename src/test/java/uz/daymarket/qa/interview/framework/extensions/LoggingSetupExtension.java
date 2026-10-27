package uz.daymarket.qa.interview.framework.extensions;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.MDC;

public class LoggingSetupExtension implements BeforeEachCallback, AfterEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        String testName = context.getRequiredTestClass().getPackageName() + "."
                + context.getRequiredTestClass().getSimpleName() + "."
                + context.getRequiredTestMethod().getName();

        MDC.put("testName", testName);
    }


    @Override
    public void afterEach(ExtensionContext context) {
        MDC.remove("testName");
    }
}
