package com.mateusz.controller;

import com.mateusz.EmailManger;
import com.mateusz.controller.services.LoginService;
import com.mateusz.model.EmailAccount;
import com.mateusz.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController extends BaseController implements Initializable {

    @FXML
    private TextField emailAddressField;

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    public LoginWindowController(EmailManger emailManger, ViewFactory viewFactory, String fxmlName) {
        super(emailManger, viewFactory, fxmlName);
    }

    @FXML
    void loginButtonAction() {
        boolean filedAreValid;
        if (filedAreValid()) {
            EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(),
                    passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailManger);
            loginService.start();
            loginService.setOnSucceeded(event -> {
                EmailLoginResult emailLoginResult = loginService.getValue();

                switch (emailLoginResult) {
                    case SUCCESS:
                        System.out.println("login succesfull!!!" + emailAccount);
                        if(!viewFactory.isMainViewInitialized()){
                            viewFactory.showMainWindow();
                        }
                        Stage stage = (Stage) errorLabel.getScene().getWindow();
                        viewFactory.closeStage(stage);
                        return;
                    case FAILED_BY_CREDENTIALS:
                        errorLabel.setText("invalid credentials!");
                        return;
                    case FAILED_BY_UNEXPECTED_ERROR:
                        errorLabel.setText("unexpected error!");
                        return;
                }
            });

            }
        }


    private boolean filedAreValid() {
        if (emailAddressField.getText().isEmpty()) {
            errorLabel.setText("Please fill eamil");
            return false;
        }
        if (passwordField.getText().isEmpty()) {
            errorLabel.setText("Please fill eamil");
            return false;
        }

        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailAddressField.setText("");
        passwordField.setText("");
    }
}
