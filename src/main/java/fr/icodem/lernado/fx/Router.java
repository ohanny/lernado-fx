package fr.icodem.lernado.fx;

import fr.icodem.lernado.fx.views.View;
import fr.icodem.lernado.fx.views.ViewFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;

public class Router {
    @Inject private ViewFactory viewFactory;
    @Inject private Stage stage;

    public void navigate(String target) {
        View view = viewFactory.getView(target);
        Parent parent = view.getPane();

        Scene scene = parent.getScene();
        if (scene == null) {
            scene = new Scene(parent);
        }

        stage.setScene(scene);
        view.setTitle(stage);
    }

}
