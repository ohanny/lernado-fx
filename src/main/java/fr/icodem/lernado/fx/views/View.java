package fr.icodem.lernado.fx.views;

import com.google.common.base.CaseFormat;
import fr.icodem.lernado.fx.context.LernadoInjector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class View {

    private Parent pane;
    private String resourceName;
    private ResourceBundle bundle;
    private Object presenter;

    @Inject @Named("app.name") private String appName;

    public static <T> T getInstance(Class<T> clazz) {
        return LernadoInjector.Instance.getInstance(clazz);
    }

    // should be called by router only, i.e. on root views
    public Parent getPane() {
        if (pane == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(getFXMLName()));
            loader.setControllerFactory(View::getInstance);
            try {
                pane = loader.load();
                addResources();
                presenter = loader.getController();
            } catch (IOException e) {
                throw new RuntimeException("Failed loading FXML : " + getClass(), e);
            }
        }
        return pane;
    }

    private void addResources() {
        String cssResourceName = getClass().getPackage().getName().replaceAll("\\.", "/")
                + "/" + getResourceName() + ".css";
        pane.getStylesheets().add(cssResourceName);
    }

    private String getViewName() {
        String viewName = getClass().getSimpleName();
        int viewIndex = viewName.lastIndexOf("View");
        return viewName.substring(0, viewIndex);
    }

    /**
     * Resource is .fxml, .css, .properties
     * @return the name of the resource
     */
    private String getResourceName() {
        if (resourceName == null) {
            resourceName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, getViewName());
        }
        return resourceName;
    }

    private String getFXMLName() {
        return getResourceName() + ".fxml";
    }

    private ResourceBundle getBundle() {
        try {
            if (bundle == null) {
                String bundleName = getClass().getPackage().getName() + "." + getResourceName();
                bundle = ResourceBundle.getBundle(bundleName);
            }
        } catch (MissingResourceException mre) {
            System.out.println(mre.getMessage());
            throw mre;
        }
        return bundle;
    }

    private String getProperty(String name) {
        return getBundle().getString(name);
    }

    private String getTitleKey() {
        return getResourceName() + "." + "title";
    }

    public void setTitle(Stage stage) {
        try {
            String title = getProperty(getTitleKey());
            stage.setTitle(appName + " - " + title);
        } catch (Exception e) {}
    }

    public Object getPresenter() {
        return presenter;
    }

}
