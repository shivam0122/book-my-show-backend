package com.example.project.bookmyshowbackend.Repository;

import com.example.project.bookmyshowbackend.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
}
