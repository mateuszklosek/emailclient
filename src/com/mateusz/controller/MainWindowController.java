package com.mateusz.controller;

import com.mateusz.view.EmailManger;
import com.mateusz.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;

public class MainWindowController extends BaseController {

    @FXML
    private WebView emailWebView;

    @FXML
    private TableView<?> emailsTableView;

    @FXML
    private TreeView<?> emailsTreeView;

    public MainWindowController(EmailManger emailManger, ViewFactory viewFactory, String fxmlName) {
        super(emailManger, viewFactory, fxmlName);
    }

    @FXML
    void optionsAction(ActionEvent event) {

    }

}
