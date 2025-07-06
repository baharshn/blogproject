package com.blog.Services;

import com.blog.Dto.UserDto.UserRequestDto;
import com.blog.Dto.UserDto.UserResponseDto;
import com.blog.Entities.Role;
import com.blog.Entities.Users;
import com.blog.Enums.Status;
import com.blog.Mappers.UserMapper;
import com.blog.Repositories.RoleRepository;
import com.blog.Repositories.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder=passwordEncoder;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if(userRequestDto==null){
            throw new IllegalArgumentException("UserRequestDto is null");
        }

        Role role = roleRepository.findById(userRequestDto.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        Users newUser = userMapper.toEntity(userRequestDto,role);
        newUser.setCreatedBy("System");

        //şifre hashleme
        //newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        Users savedUser = userRepository.save(newUser);
        return userMapper.toDto(savedUser);

    }

    public UserResponseDto updateUser(UserRequestDto userRequestDto,Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        if(userRequestDto.getUsername() != null){
            user.setUsername(userRequestDto.getUsername());
        }

        if(userRequestDto.getEmail() != null){
            user.setEmail(userRequestDto.getEmail());
        }

        if(userRequestDto.getFullName() != null){
            user.setFullname(userRequestDto.getFullName());
        }

        if (userRequestDto.getPassword() != null) {
            String hashed = passwordEncoder.encode(userRequestDto.getPassword());
            user.setPassword(hashed);
        }
        if(userRequestDto.getRoleId() != null){
            Role role = roleRepository.findById(userRequestDto.getRoleId())
                    .orElseThrow(() -> new IllegalArgumentException("Role not found"));
            user.setRole(role);
        }

        user.setUpdatedBy("system");

        Users updatedUser = userRepository.save(user);

        return userMapper.toDto(updatedUser);


    }

    public List<UserResponseDto> getAllActiveUsers() {//buraya parametre gelmesi gerekebilir?
        return userRepository.findByStatus(Status.ACTIVE)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserResponseDto getUserById(Long id ) {
        Users user= userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    public void deleteUser(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }
}
