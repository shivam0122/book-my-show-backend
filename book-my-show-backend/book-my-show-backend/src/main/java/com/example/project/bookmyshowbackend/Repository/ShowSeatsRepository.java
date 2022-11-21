package com.example.project.bookmyshowbackend.Repository;

import com.example.project.bookmyshowbackend.model.ShowSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatsRepository extends JpaRepository<ShowSeatEntity, Integer> {
}
