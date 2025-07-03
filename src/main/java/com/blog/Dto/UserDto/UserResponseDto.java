package com.blog.Dto.UserDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {  //usera veri döndürmek için
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String roleName;
}
