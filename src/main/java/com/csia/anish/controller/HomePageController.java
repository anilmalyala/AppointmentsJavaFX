package com.csia.anish.controller;

import com.csia.anish.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class HomePageController extends BaseController implements Initializable {


    @Autowired
    ApplicationContext applicationContext;

    Session session=Session.getSession();

    @FXML
    public Label userName=new Label();

    public void updateHomeText(){
        userName.setText("Welcome "+Session.getUser().getUserName());
    }

    public void addStudent(ActionEvent actionEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addStudent.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        System.out.println(loader.getLocation());

        //  Parent addProductParent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Parent addProductParent = loader.load();
       // AddNewStudent controller=loader.getController();
       // controller.init();
        Scene addStudentScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addStudentScene);
        window.show();

    }

    public void bookAppointment(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addAppointments.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        System.out.println(loader.getLocation());

        //  Parent addProductParent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Parent addProductParent = loader.load();
        // AddNewStudent controller=loader.getController();
        // controller.init();
        Scene addStudentScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addStudentScene);
        window.show();
    }

    public void modifyStudent(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewStudents.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        System.out.println(loader.getLocation());

        //  Parent addProductParent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Parent addProductParent = loader.load();
        // AddNewStudent controller=loader.getController();
        // controller.init();
        Scene addStudentScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addStudentScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setText("Welcome "+Session.getUser().getUserName());
    }

    public void viewAppointments(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("allAppointment.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        System.out.println(loader.getLocation());

        //  Parent addProductParent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Parent addProductParent = loader.load();
        // AddNewStudent controller=loader.getController();
        // controller.init();
        Scene addStudentScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addStudentScene);
        window.show();
    }

    public void modifyAppointments(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Session.logout();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        System.out.println(loader.getLocation());

        //  Parent addProductParent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Parent addProductParent = loader.load();
        // AddNewStudent controller=loader.getController();
        // controller.init();
        Scene addStudentScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addStudentScene);
        window.show();
    }
}
