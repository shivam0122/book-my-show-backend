package com.example.project.bookmyshowbackend.converter;

import com.example.project.bookmyshowbackend.dto.EntryRequest.TheatreEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.TheatreResponseDto;
import com.example.project.bookmyshowbackend.model.TheatreEntity;

public class TheatreConverter {

    public static TheatreEntity convertDtoToEntity(TheatreEntryDto theatreDto){
        return TheatreEntity.builder()
                .address(theatreDto.getAddress())
                .city(theatreDto.getCity())
                .name(theatreDto.getName())
                .build();

                //should i write theatreDto.showDto()??
        //we have already discussed
    }

    public static TheatreResponseDto convertEntityToDto(TheatreEntity theatreEntity){

        return TheatreResponseDto.builder()
                .id(theatreEntity.getId())
                .name(theatreEntity.getName())
                .city(theatreEntity.getCity())
                .address(theatreEntity.getAddress())
                .build();

    }
}
