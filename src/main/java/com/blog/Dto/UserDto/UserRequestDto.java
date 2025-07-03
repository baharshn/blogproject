package com.blog.Dto.UserDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto { //kullanıcıdan veri almak için
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Long roleId;
}
