package com.mateusz.controller;

import com.mateusz.view.EmailManger;
import com.mateusz.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        System.out.println("clicked button!");
    }
}
