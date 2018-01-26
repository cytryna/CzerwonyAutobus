package com.thenewboston;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TheNewBoston extends Application {

    Stage window;
    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label label = new Label("Username:");
        GridPane.setConstraints(label, 0,0);

        TextField textField = new TextField("Bucky");
        GridPane.setConstraints(textField, 1,0);

        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0,1);

        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1,1);

        Button loginButton = new Button("Log in");
        GridPane.setConstraints(loginButton,1,2);

        grid.getChildren().addAll(label, textField,passInput,loginButton, passLabel);

        Scene scene = new Scene(grid, 300,300);
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
