package com.example.project.bookmyshowbackend.service.impl;

import com.example.project.bookmyshowbackend.Repository.ShowRepository;
import com.example.project.bookmyshowbackend.Repository.TicketRepository;
import com.example.project.bookmyshowbackend.Repository.UserRepository;
import com.example.project.bookmyshowbackend.converter.TicketConverter;
import com.example.project.bookmyshowbackend.dto.BookTicketRequestDto;
import com.example.project.bookmyshowbackend.dto.TicketDto;
import com.example.project.bookmyshowbackend.model.ShowEntity;
import com.example.project.bookmyshowbackend.model.ShowSeatEntity;
import com.example.project.bookmyshowbackend.model.TicketEntity;
import com.example.project.bookmyshowbackend.model.UserEntity;
import com.example.project.bookmyshowbackend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public TicketDto getTicket(int id) {

        TicketEntity ticketEntity = ticketRepository.findById(id).get();

        TicketDto ticketResponseDto = TicketConverter.convertEntityToDto(ticketEntity);

        return ticketResponseDto;

    }

    @Override
    public TicketDto bookTicket(BookTicketRequestDto bookTicketRequestDto) {


        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getId()).get();
        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();

        Set<String> requestSeats = bookTicketRequestDto.getRequestedSeats();


        List<ShowSeatEntity> showSeatsEntityList = showEntity.getShowSeats();


        //Option 1
        List<ShowSeatEntity> bookedSeats = showSeatsEntityList
                .stream()
                .filter(seat -> seat.getSeatType().equals(bookTicketRequestDto.getSeatType())&&!seat.isBooked()&&
                        requestSeats.contains(seat.getSeatNumber()))
                .collect(Collectors.toList());


        //Option 2
//        List<ShowSeatsEntity> bookedSeats1 = new ArrayList<>();
//
//        for(ShowSeatsEntity seat :showSeatsEntityList){
//
//            if(!seat.isBooked()&&seat.getSeatType().equals(bookTicketRequestDto.getSeatType())&&requestSeats.contains(seat.getSeatNumber())){
//                bookedSeats1.add(seat);
//            }
//        }

        if(bookedSeats.size()!=requestSeats.size()){
            //Al the seats were not avaiable
            throw new Error("All Seats not available");
        }

        //Step 2

        TicketEntity ticketEntity = TicketEntity.builder().
                user(userEntity)
                .show(showEntity)
                .seats(bookedSeats).
                build();



        //Step 3 :

        double amount = 0;

        for(ShowSeatEntity showSeatsEntity: bookedSeats){

            showSeatsEntity.setBooked(true);
            showSeatsEntity.setBookedAt(new Date());
            showSeatsEntity.setTicket(ticketEntity);

            amount = amount + showSeatsEntity.getRate();
        }

        ticketEntity.setBookedAt(new Date());
        ticketEntity.setAllottedSeats(convertListOfSeatsEntityToString(bookedSeats));
        ticketEntity.setAmount(amount);


        //Connect in thw Show and the user

        showEntity.getTickets().add(ticketEntity);


        //Add the ticket in the tickets list of the user Entity
        userEntity.getListOfTickets().add(ticketEntity);


        ticketEntity = ticketRepository.save(ticketEntity);

        return TicketConverter.convertEntityToDto(ticketEntity);


    }

    public String convertListOfSeatsEntityToString(List<ShowSeatEntity> bookedSeats){

        String str = "";
        for(ShowSeatEntity showSeatsEntity : bookedSeats){

            str = str + showSeatsEntity.getSeatNumber()+" ";
        }

        return str;

    }
}
