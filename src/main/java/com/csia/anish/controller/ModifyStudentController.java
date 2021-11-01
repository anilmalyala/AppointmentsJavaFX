package com.csia.anish.controller;

import com.csia.anish.data.Student;
import com.csia.anish.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
@Getter
@Setter
@Component
public class ModifyStudentController extends BaseController implements Initializable {


    @FXML public Button updateButton;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    StudentService studentService;

    @Autowired
    HomePageController homePageController;

    private Long studentId;

    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField classDetails;
    @FXML private TextField curriculum;
    @FXML private DatePicker dateOfBirth;
    @FXML private TextField emailAddress;

    public void setStudentId(Long studentId){
        this.studentId=studentId;
        Student student=studentService.getStudent(this.studentId).get();
        id.setText(String.valueOf(student.getStudentId()));
        name.setText(student.getName());
        classDetails.setText(student.getClassDetails());
        curriculum.setText(student.getCurriculum());
        //LocalDate date=LocalDate.parse(student.getDateOfBirth());
        dateOfBirth.setValue(LocalDate.parse(student.getDateOfBirth()));
        emailAddress.setText(student.getEmailAddress());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void updateStudent(ActionEvent actionEvent) throws IOException {
        Student student=new Student();
        student.setStudentId(studentId);
        student.setName(name.getText());
        student.setClassDetails(classDetails.getText());
        student.setCurriculum(curriculum.getText());
        student.setEmailAddress(emailAddress.getText());
        student.setDateOfBirth(dateOfBirth.getValue().toString());
        Student updatedStudent=studentService.updateStudent(student);
        if(updatedStudent!=null) {
            //showAlert("Student record updated Sucessfully", Alert.AlertType.INFORMATION);
            //updateButton.fire();
            homePageController.modifyStudent(actionEvent);

        }else{
            showAlert("Student record failed to update", Alert.AlertType.ERROR);
            displayHomePage(actionEvent);
        }

    }
}
