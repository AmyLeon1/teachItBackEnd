package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.Appointment;
import com.teachit.teachItBackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUser(User user);

    List<Appointment> findByStudentEmail(String studentEmail);

    Appointment findByUserAndDate(User user, String date);

    Appointment findByUserAndDateAndTime(User user, String date, String time);

}
