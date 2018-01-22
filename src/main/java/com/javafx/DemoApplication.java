package com.javafx;

import com.diligentia.repository.SystemRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories("com.diligentia.czerwony.repository")
@EntityScan("com.diligentia.czerwony.model")
public class DemoApplication extends Application {

    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person("Jacob", "Smith", "jacob.smith@example.com"),
                    new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                    new Person("Ethan", "Williams", "ethan.williams@example.com"),
                    new Person("Emma", "Jones", "emma.jones@example.com"),
                    new Person("Michael", "Brown", "michael.brown@example.com")
            );

    private ConfigurableApplicationContext springContext;
    Stage window;

    @Autowired
    SystemRepository systemRepository;

    @PostConstruct
    public void afterStart() {
        System.err.println("systemRepository 1 "+systemRepository);
        systemRepository.findAll().stream().forEach(expense -> System.err.println(expense.getName()));

        System.err.println("radek 3");


    }

    @Override
    public void init() throws Exception {

        springContext = SpringApplication.run(DemoApplication.class);
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample.fxml"));
//        fxmlLoader.setControllerFactory(springContext::getBean);
//        root = fxmlLoader.load();
    }


    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        Scene scene = new Scene(new Group());
        window.setTitle("Table View Sample");
        window.setWidth(300);
        window.setHeight(500);

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> refreshTable());

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        System.err.println("radek 1");
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(refreshButton, label, table);


        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        window.setScene(scene);
        window.show();
    }

    private void refreshTable() {
        System.err.println("systemRepository 2 "+systemRepository);
        systemRepository.findAll().stream().forEach(expense -> data.add(new Person(expense.getName(), expense.getAmount().toString(), "jacob.smith@example.com")));
        table.setItems(data);
        table.refresh();
    }

//    public void fillTheTable() {
//        System.err.println(systemRepository);
//        table.setItems(data);
//    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        window = primaryStage;
//        window.setTitle("Czerwony autobus");
//
//        Label label1 = new Label("Welcome to the forst scene");
//        Button button1 = new Button("Go to scene 2");
//        button1.setOnAction(e -> BoxAlert.show("Title", "Error code"));
//
//        VBox layout1 = new VBox(20);
//        layout1.getChildren().addAll(label1, button1);
//        scene1 = new Scene(layout1, 200,200);
//
//
//        window.setScene(scene1);
//        window.show();
//    }



    @Override
    public void stop() throws Exception {
        springContext.stop();
    }


//    public static void main(String[] args) {
//        launch(DemoApplication.class, args);
//    }
}
