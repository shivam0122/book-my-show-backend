package com.example.project.bookmyshowbackend.converter;

import com.example.project.bookmyshowbackend.dto.TicketDto;
import com.example.project.bookmyshowbackend.model.TicketEntity;

public class TicketConverter {

    public static TicketDto convertEntityToDto(TicketEntity ticketEntity){

        return TicketDto.builder().id((int) ticketEntity.getId()).amount(ticketEntity.getAmount())
                .alloted_seats(ticketEntity.getAllottedSeats())
                .build();

    }
}
