package com.example.project.bookmyshowbackend.converter;

import com.example.project.bookmyshowbackend.dto.EntryRequest.ShowEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.ShowResponseDto;
import com.example.project.bookmyshowbackend.model.ShowEntity;

public class ShowConverter {

    public static ShowEntity convertDtoToEntity(ShowEntryDto showDto){

        return ShowEntity.builder()
                .showDate(showDto.getShowDate())
                .showTime(showDto.getShowTime())
                .build();
    }

    public static ShowResponseDto convertEntityToDto(ShowEntity showEntity, ShowEntryDto showEntryDto){

        return ShowResponseDto.builder()
                .id(showEntity.getId())
                .showTime(showEntity.getShowTime())
                .showDate(showEntity.getShowDate())
                .movieResponseDto(showEntryDto.getMovieResponseDto())
                .theatreResponseDto(showEntryDto.getTheatreResponseDto())
                .build();
    }
}
