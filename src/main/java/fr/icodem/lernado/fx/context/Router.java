package fr.icodem.lernado.fx.context;

import com.google.inject.Injector;
import fr.icodem.lernado.fx.views.View;
import fr.icodem.lernado.fx.views.ViewFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;

public class Router {
    @Inject private ViewFactory viewFactory;
    @Inject private Stage stage;
    @Inject private NavigationScope scope;
    @Inject private Injector injector;

    public void navigate(String target) {
        navigate(target, false);
    }

    public void navigate(String target, Object data) {
        navigate(target, data, false);
    }

    public void navigate(String target, boolean newStage) {
        navigate(target, null, newStage);
    }

    public void navigate(String target, Object data, boolean newStage) {
        try {
            scope.enter();

            View view = viewFactory.getView(target);
            Parent parent = view.getPane();

            Scene scene = parent.getScene();
            if (scene == null) {
                scene = new Scene(parent);
                scene.getStylesheets().add(
                        getClass().getResource("/fr/icodem/lernado/fx/views/application.css").toExternalForm());
            }

            Stage stage = this.stage;
            if (newStage) {
                stage = new Stage();
            }
            stage.setScene(scene);
            view.setTitle(stage);

            if (data != null) {
                NavigationEventBus eventBus = injector.getInstance(NavigationEventBus.class);
                eventBus.post(data);
            }

            if (newStage) {
                stage.show();
            }

        } finally {
            scope.exit();
        }

    }
}
