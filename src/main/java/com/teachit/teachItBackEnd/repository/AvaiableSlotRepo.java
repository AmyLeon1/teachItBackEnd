package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.AvailableDate;
import com.teachit.teachItBackEnd.model.AvailableTime;
import com.teachit.teachItBackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaiableSlotRepo extends JpaRepository<AvailableDate, String> {

    //find by date,time and user

    List<AvailableDate> findByUser(User user);

    AvailableDate findByDate(String date);

    AvailableDate findByUserAndDate(User user, String date);
}
