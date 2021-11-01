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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

@Component
public class EditAppointmentController extends BaseController implements Initializable {


    @FXML private ChoiceBox typeChoiceBox;
    @FXML private DatePicker appointmentDatePicker;
    @FXML private ChoiceBox timeChoiceBox;
    @FXML private TableView<Student> studentsTable;
    @FXML private TableColumn id;
    @FXML private TableColumn name;
    @FXML private ChoiceBox coOrdinatorChoiceBox;

    private Long appointmentId;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    StudentService studentService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    HomePageController homePageController;

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

    public void displayAppointmentDetails(Long id) {
        appointmentId=id;

        Appointment appointment=appointmentService.getAppointmentById(appointmentId).get();
        coOrdinatorChoiceBox.getSelectionModel().select(appointment.getCoordinatorName());
        timeChoiceBox.getSelectionModel().select(appointment.getTime());
        typeChoiceBox.getSelectionModel().select(appointment.getAppointmentType());
        appointmentDatePicker.setValue(LocalDate.parse(appointment.getAppointmentDate()));
        int studentIndex=0;
        studentIndex=IntStream.range(0, students.size())
                .filter(idx-> students.get(idx).getStudentId().equals(appointment.getStudentId()))
                .findFirst()
                .getAsInt();
        studentsTable.getSelectionModel().select(studentIndex);



    }





    public void updateAppointment(ActionEvent actionEvent) throws IOException {

        Appointment appointment=new Appointment();
        appointment.setId(appointmentId);
        appointment.setStudentId(studentsTable.getSelectionModel().getSelectedItem().getStudentId());
        appointment.setAppointmentDate(appointmentDatePicker.getValue().toString());
        appointment.setAppointmentType(typeChoiceBox.getSelectionModel().getSelectedItem().toString());
        appointment.setCoordinatorName(coOrdinatorChoiceBox.getSelectionModel().getSelectedItem().toString());
        appointment.setTime(timeChoiceBox.getSelectionModel().getSelectedItem().toString());
        appointmentService.createOrUpdateAppointment(appointment);
        showAlert("Appointment Updated Successfully", Alert.AlertType.INFORMATION);
        homePageController.viewAppointments(actionEvent);
    }
}
