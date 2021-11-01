package com.csia.anish.repo;

import com.csia.anish.data.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends CrudRepository<Appointment,Long> {
}
