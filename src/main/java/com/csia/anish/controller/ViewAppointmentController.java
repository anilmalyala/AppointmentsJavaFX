package com.csia.anish.controller;

import com.csia.anish.data.Appointment;
import com.csia.anish.data.AppointmentDTO;
import com.csia.anish.data.Student;
import com.csia.anish.service.AppointmentService;
import com.csia.anish.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ViewAppointmentController extends BaseController implements Initializable {


    @FXML public TableView<AppointmentDTO> appointmentTableView;
    public Button exitButton;
    @FXML public TableColumn appointmentIdColumn;
    @FXML public TableColumn studentIdColumn;
    @FXML public TableColumn coOrdinatorNameColumn;
    @FXML public TableColumn typeNameColumn;
    @FXML public TableColumn appointmentDateColumn;
    @FXML public TableColumn timeColumn;

    @Autowired
    ApplicationContext applicationContext;


    @Autowired
    AppointmentService appointmentService;



    private ObservableList<String> timesList = FXCollections.observableArrayList("9:00 AM", "10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM");
    private ObservableList<String> contactList = FXCollections.observableArrayList("Nitya Menon", "Madeline Allen", "Miles Allen", "Margaux Allen");
    //private ObservableList<String> locationList = FXCollections.observableArrayList("New York", "Boise");
    private ObservableList<String> typeList = FXCollections.observableArrayList("Subject Related", "Co Circular", "Exam Related", "Others");
    private ObservableList<AppointmentDTO> appointmentObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentObservableList= FXCollections.observableArrayList();
        appointmentObservableList.addAll(appointmentService.getAllAppointments());
        appointmentTableView.setItems(appointmentObservableList);

        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory("studentName"));
        coOrdinatorNameColumn.setCellValueFactory(new PropertyValueFactory("coordinatorName"));
        typeNameColumn.setCellValueFactory(new PropertyValueFactory("appointmentType"));
        appointmentDateColumn.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        timeColumn.setCellValueFactory(new PropertyValueFactory("time"));

    }



    public void modifyAppointment(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editAppointment.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        System.out.println(loader.getLocation());

        //  Parent addProductParent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Parent addProductParent = loader.load();
        EditAppointmentController controller=loader.getController();
        controller.displayAppointmentDetails(appointmentTableView.getSelectionModel().getSelectedItem().getId());
        Scene addStudentScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(addStudentScene);
        window.show();


    }

    public void deleteAppointment(ActionEvent actionEvent) {
            appointmentService.deleteAppointment(appointmentTableView.getSelectionModel().getSelectedItem().getId());
            appointmentObservableList= FXCollections.observableArrayList();
            appointmentObservableList.addAll(appointmentService.getAllAppointments());
            appointmentTableView.setItems(appointmentObservableList);
    }
}
