package com.mateusz.controller;

import com.mateusz.EmailManger;
import com.mateusz.view.ViewFactory;

public abstract class BaseController {

    protected EmailManger emailManger;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(EmailManger emailManger, ViewFactory viewFactory, String fxmlName) {
        this.emailManger = emailManger;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
