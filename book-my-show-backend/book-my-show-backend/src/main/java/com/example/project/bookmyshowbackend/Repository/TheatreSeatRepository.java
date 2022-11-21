package com.example.project.bookmyshowbackend.Repository;

import com.example.project.bookmyshowbackend.model.TheatreEntity;
import com.example.project.bookmyshowbackend.model.TheatreSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreSeatRepository extends JpaRepository<TheatreSeatEntity, Integer> {
}
