package com.mateusz.controller;

import com.mateusz.view.EmailManger;
import com.mateusz.view.ViewFactory;

public abstract class BaseController {

    private EmailManger emailManger;
    private ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(EmailManger emailManger, ViewFactory viewFactory, String fxmlName) {
        this.emailManger = emailManger;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }
}
