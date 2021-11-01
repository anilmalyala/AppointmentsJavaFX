package com.csia.anish.controller;

import com.csia.anish.data.Student;
import com.csia.anish.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ViewStudentController extends BaseController implements Initializable {
    @FXML public TableView<Student> studentsTable;
    @FXML public TableColumn<Student, String> studentName_tc;
    @FXML public TableColumn<Student,String> classDetails_tc;
    @FXML public TableColumn<Student,String> curriculum_tc;
    @FXML public TableColumn<Student,String> emailAddress_tc;
    @FXML public TableColumn<Student,Long> studentId_tc;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    StudentService studentService;

    private ObservableList<Student> students;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        students= FXCollections.observableArrayList();
        List<Student> studentList=studentService.getAllStudents();
        students.addAll(studentList);
        studentId_tc.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentName_tc.setCellValueFactory(new PropertyValueFactory<>("name"));
        classDetails_tc.setCellValueFactory(new PropertyValueFactory<>("classDetails"));
        emailAddress_tc.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        curriculum_tc.setCellValueFactory(new PropertyValueFactory<>("curriculum"));
        studentsTable.setItems(students);
    }

    public void updateStudent(ActionEvent actionEvent) throws IOException {

        ModifyStudentController controller= (ModifyStudentController) loadFXML("updateStudent.fxml",actionEvent);
        controller.setStudentId(studentsTable.getSelectionModel().getSelectedItem().getStudentId());

    }
}
