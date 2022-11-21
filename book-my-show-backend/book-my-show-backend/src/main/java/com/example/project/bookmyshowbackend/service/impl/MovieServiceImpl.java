package com.example.project.bookmyshowbackend.service.impl;

import com.example.project.bookmyshowbackend.Repository.MovieRepository;
import com.example.project.bookmyshowbackend.converter.MovieConverter;
import com.example.project.bookmyshowbackend.dto.EntryRequest.MovieEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieResponseDto;
import com.example.project.bookmyshowbackend.model.MovieEntity;
import com.example.project.bookmyshowbackend.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public MovieResponseDto addMovie(MovieEntryDto movieDto){

//        if movie already created then we throw an exception that movie already exists.
        MovieResponseDto movieResponseDto = null;

        if(movieRepository.existsByName(movieDto.getName())){
            movieResponseDto.setName("Movie already exist in database");
            return movieResponseDto;
        }

        log.info("Adding the movie" + movieDto);

        MovieEntity movieEntity = MovieConverter.convertDtoToEntity(movieDto);
        movieEntity = movieRepository.save(movieEntity);

        movieResponseDto = MovieConverter.convertEntityToDto(movieEntity);

        return movieResponseDto;


    }

    @Override
    public MovieResponseDto getMovie(int id) {

        MovieEntity movieEntity = movieRepository.findById(id).get();

        MovieResponseDto movieResponseDto = MovieConverter.convertEntityToDto(movieEntity);
        return movieResponseDto;

    }


}
