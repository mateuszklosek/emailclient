package com.mateusz.controller;

import com.mateusz.EmailManger;
import com.mateusz.controller.services.LoginService;
import com.mateusz.model.EmailAccount;
import com.mateusz.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController extends BaseController {

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
            EmailLoginResult emailLoginResult = loginService.login();

            switch (emailLoginResult) {
                case SUCCESS:
                    System.out.println("login succesfull!!!" + emailAccount);
            }
        }

        System.out.println("clicked button!");
        viewFactory.showMainWindow();
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        viewFactory.closeStage(stage);


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
}
