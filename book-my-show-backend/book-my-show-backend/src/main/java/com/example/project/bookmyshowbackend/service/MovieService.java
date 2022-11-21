package com.example.project.bookmyshowbackend.service;

import com.example.project.bookmyshowbackend.dto.EntryRequest.MovieEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieResponseDto;

public interface MovieService {
    MovieResponseDto addMovie(MovieEntryDto movieEntryDto);

    MovieResponseDto getMovie(int id);
}
