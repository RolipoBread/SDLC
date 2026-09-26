package com.example.laba1;

import com.example.laba1.controller.MainController;
import com.example.laba1.model.ExcuseGenerator;
import com.example.laba1.model.ExcuseModel;
import com.example.laba1.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class ExcuseApplication extends Application {

    @Override
    public void start(Stage stage) {
        ExcuseModel model = new ExcuseModel();
        ExcuseGenerator generator = new ExcuseGenerator();

        MainView view = new MainView(model);

        view.init(stage);

        MainController controller = new MainController(model, generator, view);
        controller.bindHandlers();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}