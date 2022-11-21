package com.example.project.bookmyshowbackend.service;

import com.example.project.bookmyshowbackend.dto.EntryRequest.TheatreEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.TheatreResponseDto;

public interface TheatreService {

    TheatreResponseDto addTheatre(TheatreEntryDto theatreEntryDto);


    TheatreResponseDto getTheatre(int id);
}
