package fr.icodem.lernado.fx.views.home;

import fr.icodem.lernado.fx.context.LernadoContext;
import fr.icodem.lernado.fx.context.Router;
import fr.icodem.lernado.fx.domain.Course;
import fr.icodem.lernado.fx.events.SearchCourseResultEvent;
import fr.icodem.lernado.fx.services.CourseService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePresenter implements Initializable {

    @FXML TextField queryTextField;

    @Inject LernadoContext context;
    @Inject Router router;
    @Inject CourseService service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void search() {
        String query = queryTextField.getText();

        service.searchCourse(query)
               .thenAccept(this::onSearchResult)
               .exceptionally(this::onSearchFailed);
    }

    private void onSearchResult(ObservableList<Course> courses) {
        router.navigate("search-course");
        context.post(SearchCourseResultEvent.of(courses));
    }

    private Void onSearchFailed(Throwable t) {
        t.printStackTrace();
        return null;
    }

}
