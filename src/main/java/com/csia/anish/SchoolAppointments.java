package com.csia.anish;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;


@SpringBootApplication

public class SchoolAppointments extends Application {
    private ConfigurableApplicationContext springContext;
    private Parent parentNode;

    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(parentNode, 800, 600);
        stage.setTitle("Welcome to School Appointments!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws IOException {
        SpringApplicationBuilder builder=new SpringApplicationBuilder(SchoolAppointments.class);
        builder.application().setWebApplicationType(WebApplicationType.NONE);
        springContext=builder.run(getParameters().getRaw().toArray(new String[0]));
        FXMLLoader fxmlLoader = new FXMLLoader(SchoolAppointments.class.getResource("login.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        parentNode=fxmlLoader.load();
        parentNode.prefHeight(400);
        parentNode.prefWidth(600);
    }

    public static void main(String[] args) {
        launch();
    }
}