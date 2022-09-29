package com.im.port.vo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.im.port.vo.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String email;
    
    private String password;
    
    private String role; // ROLE_USER, ROLE_ADMIN
    
    // 구글 로그인
    @ColumnDefault("'N'")
    private String provider; // "google"
    
    @ColumnDefault("'N'")
    private String providerId; // "sub"
    
    @CreationTimestamp
    private Timestamp reg_date;

    public UserDto toDto(){
        return UserDto.builder()
                .id(id)
                .username(username)
                .email(email)
                .password(password)
                .role(role)
                .provider(provider)
                .providerId(providerId)
                .reg_date(reg_date)
                .build();
    }
}
