package com.example.project.bookmyshowbackend.converter;

import com.example.project.bookmyshowbackend.dto.EntryRequest.UserEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.UserResponseDto;
import com.example.project.bookmyshowbackend.model.UserEntity;

public class UserConverter {
    public static UserEntity convertDtoToEntity(UserEntryDto userEntryDto){

        //builder() is a method

        //I need to create the User
        return UserEntity.builder()
                .name(userEntryDto.getName())
                .mobile(userEntryDto.getMobileNumber())
                .build();

        //Second method for creating the object ??
        //Using the new keyword
    }

    public static UserResponseDto convertEntityToDto(UserEntity user){

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .mobNo(user.getMobile())
                .build();



    }
}
