package fr.icodem.lernado.fx.views.courses.search;

import com.google.common.eventbus.Subscribe;
import fr.icodem.lernado.fx.LernadoContext;
import fr.icodem.lernado.fx.Router;
import fr.icodem.lernado.fx.domain.Course;
import fr.icodem.lernado.fx.events.SearchCourseResultEvent;
import fr.icodem.lernado.fx.events.ShowCourseDetailEvent;
import fr.icodem.lernado.fx.services.CourseService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchCoursePresenter implements Initializable {

    @FXML TextField queryTextField;
    @FXML ListView<Course> listView;

    @Inject LernadoContext context;
    @Inject Router router;
    @Inject CourseService service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        context.register(this);
        addListeners();
    }

    private void addListeners() {
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            router.navigate("course-detail");
            context.post(ShowCourseDetailEvent.of(newValue));
        });
    }

    public void search() {
        String query = queryTextField.getText();

        service.searchCourse(query)
               .thenAccept(this::onSearchResult)
               .exceptionally(this::onSearchFailed);
    }

    private void onSearchResult(ObservableList<Course> courses) {
        listView.setItems(courses);
    }

    private Void onSearchFailed(Throwable t) {
        t.printStackTrace();
        return null;
    }

    @Subscribe
    public void onSearchResult(SearchCourseResultEvent event) {
        listView.setItems(event.getCourses());
    }

}
