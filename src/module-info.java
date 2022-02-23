module Emailclient {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;

    opens com.mateusz;
    opens com.mateusz.view;
    opens com.mateusz.controller;
}