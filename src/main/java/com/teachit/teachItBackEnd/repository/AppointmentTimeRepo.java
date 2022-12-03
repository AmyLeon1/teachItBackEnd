package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.AppointmentTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentTimeRepo extends JpaRepository<AppointmentTime, Long> {

    AppointmentTime findByTime(String time);


//    List<AppointmentTime> findByAppointmentDatesDate(String date);

    List<AppointmentTime> findByAppointmentDatesId(Long id);
}
