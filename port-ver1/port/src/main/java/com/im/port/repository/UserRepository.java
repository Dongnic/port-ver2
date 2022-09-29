package com.im.port.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.im.port.vo.dto.UserDto;
import com.im.port.vo.entity.UserEntity;

// CRUD 함수를 JpaRepository가 들고 있음
// @Repository라는 어노테이션이 없어도 Ioc된다. 이유는 상속받았기 때문에
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // findBy규칙 - > Username문법
    // select * from user where username = parameter의 username
    public UserDto findByUsername(String username); // Jpa query methods
    Optional<UserDto> findByProviderAndProviderId(String provider, String providerId);
}
