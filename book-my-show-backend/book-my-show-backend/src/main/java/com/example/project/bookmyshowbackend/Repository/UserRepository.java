package com.example.project.bookmyshowbackend.Repository;

import com.example.project.bookmyshowbackend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Integer> {

}
