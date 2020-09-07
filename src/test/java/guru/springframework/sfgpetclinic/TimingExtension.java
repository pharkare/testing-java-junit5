package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    public static final String START_TIME = "start_time";

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        getStore(extensionContext).put(START_TIME, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        Method testMethod = extensionContext.getRequiredTestMethod();
        long startTime = getStore(extensionContext).remove(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Method " + testMethod.getName() +
                " took " + duration + " msec");
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }
}
