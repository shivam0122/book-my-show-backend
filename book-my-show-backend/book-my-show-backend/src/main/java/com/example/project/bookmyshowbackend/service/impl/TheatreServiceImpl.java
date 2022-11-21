package com.example.project.bookmyshowbackend.service.impl;

import com.example.project.bookmyshowbackend.Repository.TheatreRepository;
import com.example.project.bookmyshowbackend.Repository.TheatreSeatRepository;
import com.example.project.bookmyshowbackend.converter.TheatreConverter;
import com.example.project.bookmyshowbackend.dto.EntryRequest.TheatreEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.TheatreResponseDto;
import com.example.project.bookmyshowbackend.enums.SeatType;
import com.example.project.bookmyshowbackend.enums.TheatreType;
import com.example.project.bookmyshowbackend.model.TheatreEntity;
import com.example.project.bookmyshowbackend.model.TheatreSeatEntity;
import com.example.project.bookmyshowbackend.service.TheatreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    TheatreSeatRepository theatreSeatRepository;

    @Override
    public TheatreResponseDto addTheatre(TheatreEntryDto theatreEntryDto) {

        TheatreEntity theatreEntity = TheatreConverter.convertDtoToEntity(theatreEntryDto);


        //create the Seats
        List<TheatreSeatEntity> seats = createTheatreSeat();

        theatreEntity.setSeats(seats);
        //I need to set the theaterId for all these seats

        theatreEntity.setShows(null);

        for(TheatreSeatEntity theatreSeatsEntity:seats){
            theatreSeatsEntity.setTheatre(theatreEntity);
        }
        theatreEntity.setType(TheatreType.SINGLE);

        log.info("The theater entity is "+ theatreEntity);

        theatreEntity = theatreRepository.save(theatreEntity);

        TheatreResponseDto theatreResponseDto = TheatreConverter.convertEntityToDto(theatreEntity);


        return theatreResponseDto;

    }

    List<TheatreSeatEntity> createTheatreSeat(){


        List<TheatreSeatEntity> seats = new ArrayList<>();

        seats.add(getTheatreSeat("1A",100,SeatType.CLASSIC));
        seats.add(getTheatreSeat("1B",100,SeatType.CLASSIC));
        seats.add(getTheatreSeat("1C",100,SeatType.CLASSIC));
        seats.add(getTheatreSeat("1D",100,SeatType.CLASSIC));
        seats.add(getTheatreSeat("1E",100,SeatType.CLASSIC));

        seats.add(getTheatreSeat("2A",100,SeatType.PREMIUM));
        seats.add(getTheatreSeat("2B",100,SeatType.PREMIUM));
        seats.add(getTheatreSeat("2C",100, SeatType.PREMIUM));
        seats.add(getTheatreSeat("2D",100,SeatType.PREMIUM));
        seats.add(getTheatreSeat("2E",100,SeatType.PREMIUM));


        theatreSeatRepository.saveAll(seats);

        return seats;
        //Add in this TheaterSeatEntity type

    }

    TheatreSeatEntity getTheatreSeat(String seatName, int rate, SeatType seatType){

        return TheatreSeatEntity.builder().seatNumber(seatName).rate(rate).seatType(seatType).build();
    }

    //Seperate function will be create...


    @Override
    public TheatreResponseDto getTheatre(int id) {

        TheatreEntity theatreEntity = theatreRepository.findById(id).get();

        TheatreResponseDto theatreResponseDto = TheatreConverter.convertEntityToDto(theatreEntity);

        return theatreResponseDto;
    }
}