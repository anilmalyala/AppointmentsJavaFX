package com.csia.anish.service;

import com.csia.anish.data.Appointment;
import com.csia.anish.data.AppointmentDTO;
import com.csia.anish.data.Student;
import com.csia.anish.repo.AppointmentRepo;
import com.csia.anish.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    StudentRepo studentRepo;

    public Appointment createOrUpdateAppointment(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    public void deleteAppointment(Long appointmentId){
         appointmentRepo.deleteById(appointmentId);
    }

    public List<AppointmentDTO> getAllAppointments(){

        Iterable<Appointment> appointmentIterable=appointmentRepo.findAll();
        List<AppointmentDTO> appointmentDTOList=new ArrayList<>();

        appointmentIterable.forEach(appointment -> {
            Student student=studentRepo.findById(appointment.getStudentId()).get();
            appointmentDTOList.add(new AppointmentDTO(appointment.getId(), appointment.getAppointmentDate(),appointment.getTime(), appointment.getAppointmentType(), appointment.getCoordinatorName(), appointment.getStudentId(), student.getName()));
        });

        return appointmentDTOList;
    }

    public Optional<Appointment> getAppointmentById(Long appointmentId){
        return appointmentRepo.findById(appointmentId);
    }
}
