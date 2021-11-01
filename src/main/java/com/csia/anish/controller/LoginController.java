package com.csia.anish.controller;


import com.csia.anish.data.User;
import com.csia.anish.service.UserService;
import com.csia.anish.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class LoginController extends BaseController implements Initializable {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationContext applicationContext;




    @FXML
    public Label userNameLbl=new Label();
    @FXML
    public TextField userNameTextField=new TextField();
    @FXML
    public PasswordField passwordTextField =new PasswordField();

    public void login(ActionEvent actionEvent) throws IOException {

        String userId=userNameTextField.getText();
        String password=passwordTextField.getText();

        Optional<User> user=userService.validateLogin(userId,password);

        if(user!=null && !user.isEmpty()) {
            System.out.println("User Logged in");
            Session session=Session.createSession(user.get());
            displayHomePage(actionEvent);
        }else {
            showAlert("Invalid Credentials", Alert.AlertType.ERROR);
        }
    }

    public void exitApplication(ActionEvent actionEvent) {

        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user=new User();
        user.setUserId("1");
        user.setUserName("User 1");
        user.setPassword("1");

        userService.createUser(user);

        user=new User();
        user.setUserId("2");
        user.setUserName("User 2");
        user.setPassword("2");
        userService.createUser(user);


        user=new User();
        user.setUserId("3");
        user.setUserName("User 3");
        user.setPassword("3");
        userService.createUser(user);


    }
}
