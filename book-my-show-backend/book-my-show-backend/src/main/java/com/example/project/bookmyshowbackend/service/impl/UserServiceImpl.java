package com.example.project.bookmyshowbackend.service.impl;

import com.example.project.bookmyshowbackend.Repository.UserRepository;
import com.example.project.bookmyshowbackend.converter.UserConverter;
import com.example.project.bookmyshowbackend.dto.EntryRequest.UserEntryDto;
import com.example.project.bookmyshowbackend.dto.ResponseDto.UserResponseDto;
import com.example.project.bookmyshowbackend.model.UserEntity;
import com.example.project.bookmyshowbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Override
    public void addUser(UserEntryDto userEntryDto) {

        UserEntity userEntity = UserConverter.convertDtoToEntity(userEntryDto); //Cleaner
        userRepository.save(userEntity);
    }


    @Override
    public UserResponseDto getUser(int id) {

        UserEntity user = new UserEntity();

        UserEntity userEntity = userRepository.findById(id).get();

        UserResponseDto userDto = UserConverter.convertEntityToDto(userEntity);

        return userDto;
    }
}
