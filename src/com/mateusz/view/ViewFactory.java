package com.mateusz.view;

import com.mateusz.EmailManger;
import com.mateusz.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {

    private EmailManger emailManger;
    private ArrayList<Stage> activeStages;
    private boolean mainViewInitialized = false;

    public ViewFactory(EmailManger emailManger) {
        this.emailManger = emailManger;
        activeStages = new ArrayList<Stage>();
    }

    public boolean isMainViewInitialized(){
        return mainViewInitialized;
    }

    //View options handling:
    private ColorTheme colorTheme = ColorTheme.DEFAULT ;
    private FontSize fontSize = FontSize.MEDIUM;

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void showLoginWindow() {
        System.out.println("show login window ");

        BaseController controller = new LoginWindowController(emailManger,this,"LoginWindow.fxml");
        initializeStage(controller);

    }

    public void showMainWindow(){
        System.out.println("show main window ");

        BaseController controller = new MainWindowController(emailManger,this,"MainWindow.fxml");
        initializeStage(controller);
        mainViewInitialized = true;
    }

    public void showOptionsWindow(){
        System.out.println("show options window ");

        BaseController controller = new OptionsWindowController(emailManger,this,"OptionsWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e){
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    public void closeStage(Stage stageToClose){
        stageToClose.close();
        activeStages.remove(stageToClose);
    }

    public void updateStyles() {
        for (Stage stage: activeStages) {
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());
        }
    }

    public void showComposeMessageWindow(){
        System.out.println("show compose message window ");

        BaseController controller = new ComposeMessageController(emailManger,this,"ComposeMessageWindow.fxml");
        initializeStage(controller);
    }

}
