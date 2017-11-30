package fr.icodem.lernado.fx.views.login;

import com.google.common.base.Strings;
import fr.icodem.lernado.fx.LernadoContext;
import fr.icodem.lernado.fx.Router;
import fr.icodem.lernado.fx.domain.User;
import fr.icodem.lernado.fx.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPresenter implements Initializable {

    @FXML TextField usernameTextField;
    @FXML PasswordField passwordTextField;
    @FXML Label errorLabel;

    @Inject UserService service;
    @Inject LernadoContext context;
    @Inject Router router;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void login() {
        // check username
        String username = usernameTextField.getText();
        if (!Strings.isNullOrEmpty(username)) {
            errorLabel.setText("");
        } else {
            errorLabel.setText("Username is required");
            return;
        }

        // check password
        String password = passwordTextField.getText();
        if (!Strings.isNullOrEmpty(password)) {
            errorLabel.setText("");
        } else {
            errorLabel.setText("Password is required");
            return;
        }

        // do login
        service.authenticate(username, password)
               .thenAccept(this::onAuthSuccess)
               .exceptionally(this::onAuthFailure);
    }

    private void onAuthSuccess(User user) {
        router.navigate("home");
    }

    private Void onAuthFailure(Throwable t) {
        return null;
    }

}
