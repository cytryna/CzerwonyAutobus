package com.thenewboston;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TheNewBoston extends Application {

    Stage window;
    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        window.setOnCloseRequest(e -> {
            e.consume();
            closeApp();
        });

        button = new Button("Close me");
        button.setOnAction(e -> {
            closeApp();

        });

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }

    private void closeApp() {
        System.out.println("File is saved");
        boolean answer = ConfirmBox.show("Title", "Are you sure you want to close window?");
        if (answer)
            window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
