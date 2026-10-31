package uz.daymarket.qa.interview.framework.extensions;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.MDC;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoggingSetupExtension implements BeforeEachCallback, AfterEachCallback {
    private static final String TEST_NAME_LOG_KEY = "testName";
    private static final Pattern TEST_TEMPLATE_INVOCATION_PATTERN = Pattern.compile("test-template-invocation:#(\\d+)");

    @Override
    public void beforeEach(ExtensionContext context) {
        Matcher matcher = TEST_TEMPLATE_INVOCATION_PATTERN.matcher(context.getUniqueId());
        String number = matcher.find() ? "." + matcher.group(1) : "";
        String testName = getTestName(context) + number;
        MDC.put(TEST_NAME_LOG_KEY, testName);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        MDC.remove(TEST_NAME_LOG_KEY);
    }

    private static String getTestName(ExtensionContext context) {
        return context.getRequiredTestClass().getPackageName() + "."
                + context.getRequiredTestClass().getSimpleName() + "."
                + context.getRequiredTestMethod().getName();
    }
}
