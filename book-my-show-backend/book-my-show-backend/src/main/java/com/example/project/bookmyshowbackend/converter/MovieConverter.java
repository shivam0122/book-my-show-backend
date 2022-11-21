package com.example.project.bookmyshowbackend.converter;

import com.example.project.bookmyshowbackend.dto.EntryRequest.MovieEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieResponseDto;
import com.example.project.bookmyshowbackend.model.MovieEntity;

public class MovieConverter {

    public static MovieEntity convertDtoToEntity(MovieEntryDto movieEntryDto){

        return MovieEntity.builder()
                .name(movieEntryDto.getName())
                .releaseDate(movieEntryDto.getReleaseDate())
                .build();
    }

    public static MovieResponseDto convertEntityToDto(MovieEntity movieEntity){
        return MovieResponseDto.builder()
                .id(movieEntity.getId())
                .releaseDate(movieEntity.getReleaseDate())
                .name(movieEntity.getName())
                .build();
    }
}
