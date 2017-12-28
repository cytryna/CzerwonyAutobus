package com.diligentia.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@SpringBootApplication
public class DemoApplication extends Application {

    private ConfigurableApplicationContext springContext;
//    private Parent root;
    Stage window;
    Scene scene1;

//    @Autowired
//    SystemRepository systemRepository;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(DemoApplication.class);
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample.fxml"));
//        fxmlLoader.setControllerFactory(springContext::getBean);
//        root = fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Czerwony autobus");

        Label label1 = new Label("Welcome to the forst scene");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> BoxAlert.show("Title", "Error code"));

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200,200);


        window.setScene(scene1);
        window.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }


    public static void main(String[] args) {
        launch(DemoApplication.class, args);
    }
}
