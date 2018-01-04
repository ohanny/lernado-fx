package fr.icodem.lernado.fx.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.stage.Stage;

public enum LernadoInjector {
    Instance;

    private Injector injector;

    public LernadoInjector init(Stage stage) {
        injector = Guice.createInjector(new LernadoModule(stage));
        return this;
    }

    public <T> T getInstance(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

}
