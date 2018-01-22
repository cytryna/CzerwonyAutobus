package com.diligentia.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertBox {

    public static void show(String message) {
        Stage window = new Stage(StageStyle.UNDECORATED);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);


        Label label = new Label();
        label.setText(message);
        Button closebuButton = new Button("Ok");
        closebuButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        layout.getChildren().addAll(label, closebuButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
