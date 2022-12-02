package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.AppointmentDate;
import com.teachit.teachItBackEnd.model.AppointmentDatePrimaryData;
import com.teachit.teachItBackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDateRepo extends JpaRepository<AppointmentDate, Long> {
    AppointmentDate findByDate(String date);

   // List<AppointmentDate> findAppointmentDateByEmail(String email);

    List<AppointmentDate> findAllByUsersEmail(String email);


}
