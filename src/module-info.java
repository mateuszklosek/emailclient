module Emailclient {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires activation;
    requires java.mail;
    requires java.desktop;

    opens com.mateusz;
    opens com.mateusz.view;
    opens com.mateusz.controller;
    opens com.mateusz.model;
}