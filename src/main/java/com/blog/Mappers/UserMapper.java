package com.blog.Mappers;

import com.blog.Dto.UserDto.UserRequestDto;
import com.blog.Dto.UserDto.UserResponseDto;
import com.blog.Entities.Role;
import com.blog.Entities.Users;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //usera göstermek için
    public UserResponseDto toDto(Users user) {
        if (user == null) {
            return null;
        }
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setFullName(user.getFullname());
        userResponseDto.setRoleName(user.getRole().getName());
        return userResponseDto;
    }

    //yeni user eklemek için
    public Users toEntity(UserRequestDto userRequestDto, Role role) {
        if (userRequestDto == null) {
            return null;
        }
        Users user = new Users();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setFullname(userRequestDto.getFullName());
        user.setPassword(userRequestDto.getPassword());
        user.setRole(role);
        return user;
    }

    // Update işlemi için mevcut User'ı güncelle
    public void updateEntity(Users user, UserRequestDto dto, Role role) {
        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());
        if (dto.getFullName() != null) user.setFullname(dto.getFullName());
        if (role != null) user.setRole(role);
    }
}
