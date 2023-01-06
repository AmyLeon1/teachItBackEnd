package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.AppointmentDate;
import com.teachit.teachItBackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDateRepo extends JpaRepository<AppointmentDate, Long> {
    AppointmentDate findByDate(String date);

    AppointmentDate findByUsersEmailAndDate(String email, String date);

    AppointmentDate findByUsersAndDate(User user, String date);

    List<AppointmentDate> findAllByUsersEmail(String email);


}
