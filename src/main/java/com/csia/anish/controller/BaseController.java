package com.csia.anish.controller;

import com.csia.anish.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public abstract class BaseController {

    @Autowired
    ApplicationContext applicationContext;

    public void displayHomePage(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("homepage.fxml"));
        System.out.println(loader.getLocation());
        loader.setControllerFactory(applicationContext::getBean);

        //  Parent addProductParent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Parent addProductParent = loader.load();
        HomePageController controller=loader.getController();
        controller.updateHomeText();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }

    public void showAlert(String message,Alert.AlertType alertType){
        Alert alert=new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
