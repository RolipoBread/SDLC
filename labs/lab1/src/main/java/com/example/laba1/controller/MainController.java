package com.example.laba1.controller;

import com.example.laba1.model.ExcuseGenerator;
import com.example.laba1.model.ExcuseModel;
import com.example.laba1.model.Situation;
import com.example.laba1.view.MainView;


public class MainController {

    private final ExcuseModel model;
    private final ExcuseGenerator generator;
    private final MainView view;

    public MainController(ExcuseModel model, ExcuseGenerator generator, MainView view) {
        this.model = model;
        this.generator = generator;
        this.view = view;
    }

    public void bindHandlers() {
        view.getGenerateBtn().setOnAction(e -> onGenerateClicked());
        view.getInputBtn().setOnAction(e -> onInputClicked());
    }

    private void onGenerateClicked() {
        if (!model.hasUserData()) {
            view.showError("Сначала введите данные пользователя");
            return;
        }
        Situation situation = view.getSituationBox().getValue();
        if (situation == null) {
            view.showError("Выберите ситуацию");
            return;
        }

        model.setCurrentSituation(situation);
        String excuse = generator.generate(situation);
        model.setLastExcuse(excuse); // модель сама уведомит View
    }

    private void onInputClicked() {
        view.showInputDialog(this::handleDialogOk, view::closeDialog);
    }
    private void handleDialogOk() {
        String name = view.getDialogNameField().getText().trim();
        String details = view.getDialogDetailsField().getText().trim();

        if (name.isEmpty()) {
            view.getDialogErrorLabel().setText("Имя не может быть пустым");
            return;
        }
        if (name.length() < 2) {
            view.getDialogErrorLabel().setText("Имя слишком короткое");
            return;
        }
        if (details.isEmpty()) {
            view.getDialogErrorLabel().setText("Детали не могут быть пустыми");
            return;
        }
        model.setUserData(name, details);
        view.closeDialog();
    }
}