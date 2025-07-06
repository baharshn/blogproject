package com.blog.Controller;

import com.blog.Dto.AuthDto.AuthRequestDto;
import com.blog.Dto.AuthDto.AuthResponseDto;
import com.blog.Security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    /**
     * Login endpointi
     */
    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        // 1️⃣ Kimlik doğrula
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 2️⃣ UserDetails al
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 3️⃣ Token üret
        String token = jwtUtils.generateToken(userDetails.getUsername());

        // 4️⃣ Response dön
        return new AuthResponseDto(token);
    }
}
