package com.stackroute.userloginservice.repository;

import com.stackroute.userloginservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String > {
    boolean existsByEmailId(String emailId);
//    User findByEmailIdAndPassword(String emailId, String password);
}