package com.example.project.bookmyshowbackend.dto.EntryRequest;

import com.example.project.bookmyshowbackend.dto.ResponseDto.MovieResponseDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.TheatreResponseDto;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ShowEntryDto {

    @NotNull
    LocalDate showDate;

    @NotNull
    LocalTime showTime;

    @NotNull
    MovieResponseDto movieResponseDto;

    @NotNull
    TheatreResponseDto theatreResponseDto;
}
