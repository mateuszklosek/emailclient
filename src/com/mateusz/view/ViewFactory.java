package com.mateusz.view;

public class ViewFactory {

    private EmailManger emailManger;

    public ViewFactory(EmailManger emailManger) {
        this.emailManger = emailManger;
    }

    public void showLoginWindow() {
        System.out.println("show login window ");
    }
}
