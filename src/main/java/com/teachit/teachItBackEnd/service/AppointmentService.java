package com.teachit.teachItBackEnd.service;

import com.teachit.teachItBackEnd.controller.AppointmentController;
import com.teachit.teachItBackEnd.model.Appointment;
import com.teachit.teachItBackEnd.model.User;
import com.teachit.teachItBackEnd.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo repo;

    /* save appointment via the repository */
    public Appointment save(Appointment appointment) {
        return repo.save(appointment);
    }

    public List<Appointment> findByUser(User user) {
        return repo.findByUser(user);
    }

    public List<Appointment> findByStudentEmail(String studentEmail) {
        return repo.findByStudentEmail(studentEmail);
    }

    public void deleteById(long id) {
        //call repository's deleteById method and pass in supplied id
        repo.deleteById(id);
    }

    public Appointment findByUserAndDate(User user, String date) {
        return repo.findByUserAndDate(user, date);
    }

    public Appointment findByUserAndDateAndTime(User user, String date, String time) {
        return repo.findByUserAndDateAndTime(user, date, time);
    }

}
