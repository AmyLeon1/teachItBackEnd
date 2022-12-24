package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.HomeworkFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepo extends JpaRepository<HomeworkFile, String> {

    HomeworkFile findByName(String name);
}
