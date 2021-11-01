package com.csia.anish.controller;

import com.csia.anish.data.Appointment;
import com.csia.anish.data.Student;
import com.csia.anish.service.AppointmentService;
import com.csia.anish.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
@Component
public class CreateAppointmentController extends BaseController implements Initializable {


    @FXML public ChoiceBox typeChoiceBox;
    @FXML public DatePicker appointmentDatePicker;
    @FXML public ChoiceBox timeChoiceBox;
    @FXML public TableView<Student> studentsTable;
    @FXML public TableColumn id;
    @FXML public TableColumn name;
    @FXML public ChoiceBox coOrdinatorChoiceBox;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    StudentService studentService;

    @Autowired
    AppointmentService appointmentService;

    private final ObservableList<String> timesList = FXCollections.observableArrayList("9:00 AM", "10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM");
    private final ObservableList<String> contactList = FXCollections.observableArrayList("Nitya Menon", "Madeline Allen", "Miles Allen", "Margaux Allen");
    //private ObservableList<String> locationList = FXCollections.observableArrayList("New York", "Boise");
    private final ObservableList<String> typeList = FXCollections.observableArrayList("Subject Related", "Co Circular", "Exam Related", "Others");
    private ObservableList<Student> students;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        students= FXCollections.observableArrayList();
        List<Student> studentList=studentService.getAllStudents();
        students.addAll(studentList);
        id.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentsTable.setItems(students);

        coOrdinatorChoiceBox.setItems(contactList);
        timeChoiceBox.setItems(timesList);
        typeChoiceBox.setItems(typeList);
    }

    private void reset(){
        coOrdinatorChoiceBox.getSelectionModel().select(-1);
        timeChoiceBox.getSelectionModel().select(-1);
        typeChoiceBox.getSelectionModel().select(-1);
        appointmentDatePicker.setValue(null);
        studentsTable.getSelectionModel().clearSelection();
    }

    public void addAppointment(ActionEvent actionEvent) {

        Appointment appointment=new Appointment();
        appointment.setStudentId(studentsTable.getSelectionModel().getSelectedItem().getStudentId());
        appointment.setAppointmentDate(appointmentDatePicker.getValue().toString());
        appointment.setAppointmentType(typeChoiceBox.getSelectionModel().getSelectedItem().toString());
        appointment.setCoordinatorName(coOrdinatorChoiceBox.getSelectionModel().getSelectedItem().toString());
        appointment.setTime(timeChoiceBox.getSelectionModel().getSelectedItem().toString());
        appointmentService.createOrUpdateAppointment(appointment);
        showAlert("Appointment Created Successfully", Alert.AlertType.INFORMATION);

        reset();
    }
}
