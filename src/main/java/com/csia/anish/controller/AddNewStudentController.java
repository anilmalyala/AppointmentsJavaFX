package com.csia.anish.controller;

import com.csia.anish.data.Student;
import com.csia.anish.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AddNewStudentController extends BaseController implements Initializable{
    @FXML
    public DatePicker dob;
    @FXML
    private TextField name =new TextField();
    @FXML
    private TextField emailAddress =new TextField();
    @FXML
    private TextField classDetails = new TextField();
    @FXML
    private ChoiceBox curriculum =new ChoiceBox();

    @Autowired
    StudentService studentService;

    private final ObservableList<String> curriculumList = FXCollections.observableArrayList("CBSE", "ICSE", "IGCSE", "IB DP");

    public void init() {

        curriculum.setItems(curriculumList);
    }

    public void addStudent(ActionEvent actionEvent) {

        Student student=new Student();
        student.setName(name.getText());
        student.setEmailAddress(emailAddress.getText());
        student.setClassDetails(classDetails.getText());
        student.setDateOfBirth(dob.getValue().toString());
        student.setCurriculum(curriculum.getSelectionModel().getSelectedItem().toString());

        if(studentService.createStudent(student)!=null){
            showAlert("Successfully created Student", Alert.AlertType.INFORMATION);
            name.setText("");
            emailAddress.setText("");
            classDetails.setText("");
            dob.setValue(null);
            curriculum.getSelectionModel().select(-1);
        }else {
            showAlert("Error while creating Student", Alert.AlertType.ERROR);
        }
    }



    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        displayHomePage(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        curriculum.setItems(curriculumList);
    }
}
