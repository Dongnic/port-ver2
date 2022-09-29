package com.im.port.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.im.port.repository.UserRepository;
import com.im.port.vo.dto.UserDto;
import com.im.port.vo.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService{

    private final UserRepository repository;

    @Override
    public List<UserDto> getUserList() throws Exception {
        List<UserEntity> entityList = repository.findAll();
        List<UserDto> dtoList = new ArrayList<>();
        for (UserEntity entity : entityList) {
            dtoList.add(entity.toDto());
        }
        return dtoList;
    }

    @Override
    public Long postUser(UserDto userDto) throws Exception {
        return repository.save(userDto.toEntity()).getId();
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        repository.deleteById(id);
    }

    @Override
    public UserDto findUserById(Long id) throws Exception {
        return repository.findById(id).orElseThrow().toDto();
    }
    
}
