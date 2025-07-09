package com.blog.Dto.LikeDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

//kullanıcı like atarken sadece postid göndermeli user ın kim olduğunu güvenli oturumdan alınmalıdır
public class LikeRequestDto {
    private Long postId;
    private Long userId;
}

