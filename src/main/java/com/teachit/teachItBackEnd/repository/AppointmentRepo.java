package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.Appointment;
import com.teachit.teachItBackEnd.model.Blog;
import com.teachit.teachItBackEnd.model.Comment;
import com.teachit.teachItBackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUser(User user);

    List<Appointment> findByStudentEmail(String studentEmail);

    Appointment findAppByUser(User user);

    Appointment findByDate(String date);

    Appointment findByUserAndDate(User user, String date);
}
