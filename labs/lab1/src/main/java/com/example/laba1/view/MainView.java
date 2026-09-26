package com.example.laba1.view;

import com.example.laba1.model.ExcuseModel;
import com.example.laba1.model.Situation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Представление. Реализует ModelListener — обновляется автоматически
 * при изменении модели.
 */
public class MainView implements ExcuseModel.ModelListener {

    private final ExcuseModel model;

    private Stage stage;
    private ComboBox<Situation> situationBox;
    private Label dataLabel;
    private Label resultLabel;
    private Button generateBtn;
    private Button inputBtn;

    // Элементы диалога ввода
    private Stage currentDialog;
    private TextField dialogNameField;
    private TextField dialogDetailsField;
    private Label dialogErrorLabel;

    public MainView(ExcuseModel model) {
        this.model = model;
        this.model.addListener(this);
    }

    public void init(Stage stage) {
        this.stage = stage;
        buildUi();
        onModelChanged();
    }

    private void buildUi() {
        situationBox = new ComboBox<>();
        situationBox.getItems().setAll(Situation.values());
        situationBox.setValue(model.getCurrentSituation());

        generateBtn = new Button("Сгенерировать оправдание");
        generateBtn.setPrefWidth(220);

        inputBtn = new Button("Ввести данные");
        inputBtn.setPrefWidth(220);

        dataLabel = new Label("Данные не введены");
        dataLabel.setStyle("-fx-text-fill: #7f8c8d;");

        resultLabel = new Label("Тут появится оправдание");
        resultLabel.setWrapText(true);
        resultLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50;");

        VBox root = new VBox(12,
                new Label("Ситуация:"),
                situationBox,
                generateBtn,
                inputBtn,
                new Separator(),
                dataLabel,
                resultLabel
        );
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_LEFT);

        stage.setScene(new Scene(root, 420, 340));
        stage.setTitle("Генератор оправданий");
        stage.setResizable(false);
    }

    public Button getGenerateBtn() { return generateBtn; }
    public Button getInputBtn() { return inputBtn; }
    public ComboBox<Situation> getSituationBox() { return situationBox; }

    @Override
    public void onModelChanged() {
        if (dataLabel == null) return;

        if (model.hasUserData()) {
            dataLabel.setText("Имя: " + model.getSavedName()
                    + " | Детали: " + model.getSavedDetails());
        }
        if (model.getLastExcuse() != null && !model.getLastExcuse().isEmpty()) {
            resultLabel.setText(model.getLastExcuse());
        }
        situationBox.setValue(model.getCurrentSituation());
    }
    public void showInputDialog(Runnable onOk, Runnable onCancel) {
        Stage dialog = new Stage();
        dialog.initOwner(stage);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setTitle("Ввод данных");
        dialog.setResizable(false);

        dialogNameField = new TextField(model.getSavedName());
        dialogNameField.setPromptText("Ваше имя");

        dialogDetailsField = new TextField(model.getSavedDetails());
        dialogDetailsField.setPromptText("Детали (например, группа или отдел)");

        dialogErrorLabel = new Label();
        dialogErrorLabel.setStyle("-fx-text-fill: #e74c3c;");
        dialogErrorLabel.setWrapText(true);

        Button okBtn = new Button("OK");
        okBtn.setPrefWidth(100);
        Button cancelBtn = new Button("Отмена");
        cancelBtn.setPrefWidth(100);

        okBtn.setOnAction(e -> onOk.run());
        cancelBtn.setOnAction(e -> onCancel.run());

        VBox box = new VBox(10,
                new Label("Имя:"), dialogNameField,
                new Label("Детали:"), dialogDetailsField,
                dialogErrorLabel, okBtn, cancelBtn
        );
        box.setPadding(new Insets(20));
        box.setAlignment(Pos.CENTER_LEFT);

        dialog.setScene(new Scene(box, 320, 320));
        currentDialog = dialog;
        dialog.showAndWait();
    }

    public TextField getDialogNameField() { return dialogNameField; }
    public TextField getDialogDetailsField() { return dialogDetailsField; }
    public Label getDialogErrorLabel() { return dialogErrorLabel; }

    public void closeDialog() {
        if (currentDialog != null) currentDialog.close();
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}