package fr.icodem.lernado.fx;

import javafx.application.Application;
import javafx.stage.Stage;

public class LernadoApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // init injector
        LernadoInjector injector = LernadoInjector.Instance.init(stage);

        // set title
        stage.setTitle("Lernado");
        stage.setOnCloseRequest( e -> LernadoContext.shutdown());

        // navigate to login
        Router router = injector.getInstance(Router.class);
        router.navigate("login");

        stage.show();
    }
}
