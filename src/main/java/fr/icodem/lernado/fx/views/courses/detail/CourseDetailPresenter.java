package fr.icodem.lernado.fx.views.courses.detail;

import com.google.common.eventbus.Subscribe;
import fr.icodem.lernado.fx.context.LernadoContext;
import fr.icodem.lernado.fx.context.Router;
import fr.icodem.lernado.fx.events.ShowCourseDetailEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseDetailPresenter implements Initializable {

    @FXML
    Label titleLabel;

    @Inject LernadoContext context;
    @Inject Router router;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        context.register(this);
    }

    @Subscribe
    public void onShowCourseDetail(ShowCourseDetailEvent event) {
        titleLabel.setText(event.getCourse().getTitle());
    }


}
