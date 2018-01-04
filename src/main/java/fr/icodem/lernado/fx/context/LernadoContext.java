package fr.icodem.lernado.fx.context;

import com.google.common.eventbus.EventBus;
import javafx.application.Platform;

import javax.inject.Inject;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LernadoContext {

    public static ExecutorService TASK_EXECUTOR = Executors.newSingleThreadExecutor();
    public static Executor FX_PLATFORM_EXECUTOR = Platform::runLater;

    private LernadoInjector injector;
    private EventBus eventBus;

    @Inject
    public LernadoContext(LernadoInjector injector, EventBus eventBus) {
        this.injector = injector;
        this.eventBus = eventBus;

        register(this);
    }

    public void register(Object obj) {
        eventBus.register(obj);
    }

    public void post(Object event) {
        eventBus.post(event);
    }

    public static void shutdown() {
        TASK_EXECUTOR.shutdown();
    }

    // getters and setters

}
