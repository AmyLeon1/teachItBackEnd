package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//hold all basic methods for database communication
//inbuild methods from JPArepository
@Repository
public interface RegistrationRepo extends JpaRepository<User, Integer> {

    //method declaration for methods not built in to JPARepository
    //JPA repo will create these queries
    public User findByEmail(String email);
    public User findByEmailAndPassword(String email,String Password);


}
