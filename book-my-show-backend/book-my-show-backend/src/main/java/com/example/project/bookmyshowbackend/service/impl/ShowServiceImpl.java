package com.example.project.bookmyshowbackend.service.impl;

import com.example.project.bookmyshowbackend.Repository.MovieRepository;
import com.example.project.bookmyshowbackend.Repository.ShowRepository;
import com.example.project.bookmyshowbackend.Repository.ShowSeatsRepository;
import com.example.project.bookmyshowbackend.Repository.TheatreRepository;
import com.example.project.bookmyshowbackend.converter.ShowConverter;
import com.example.project.bookmyshowbackend.dto.EntryRequest.ShowEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.ShowResponseDto;
import com.example.project.bookmyshowbackend.model.*;
import com.example.project.bookmyshowbackend.service.ShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theaterRepository;

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    @Autowired
    ShowRepository showRepository;


    @Override
    public ShowResponseDto addShow(ShowEntryDto showEntryDto) {

        ShowEntity showEntity = ShowConverter.convertDtoToEntity(showEntryDto);

        //MovieEntity
        MovieEntity movieEntity = movieRepository.findById(showEntryDto.getMovieResponseDto().getId()).get();

        TheatreEntity theatreEntity = theaterRepository.findById(showEntryDto.getTheatreResponseDto().getId()).get();


        showEntity.setMovie(movieEntity);
        showEntity.setTheatre(theatreEntity);

        showEntity = showRepository.save(showEntity);


        //We need to pass the list of the theater seats
        generateShowEntitySeats(theatreEntity.getSeats(),showEntity);



        //We need to create Response Show Dto.

        ShowResponseDto showResponseDto = ShowConverter.convertEntityToDto(showEntity,showEntryDto);

        return showResponseDto;
    }

    public void generateShowEntitySeats(List<TheatreSeatEntity> theaterSeatsEntityList,ShowEntity showEntity){

        List<ShowSeatEntity> showSeatsEntityList = new ArrayList<>();

        //log.info(String.valueOf(theaterSeatsEntityList));
        log.info("The list of theaterEntity Seats");
        for(TheatreSeatEntity tse: theaterSeatsEntityList){
            log.info(tse.toString());
        }


        for(TheatreSeatEntity tse : theaterSeatsEntityList){

            ShowSeatEntity showSeatsEntity = ShowSeatEntity.builder().seatNumber(tse.getSeatNumber())
                    .seatType(tse.getSeatType())
                    .rate(tse.getRate())
                    .build();

            showSeatsEntityList.add(showSeatsEntity);
        }


        //We need to set the show Entity for each of the ShowSeat....
        for(ShowSeatEntity showSeatsEntity:showSeatsEntityList){
            showSeatsEntity.setShow(showEntity);
        }

        showSeatsRepository.saveAll(showSeatsEntityList);


        showEntity.setShowSeats(showSeatsEntityList);

    }


}
