package com.jit.backend.controller;

import com.jit.backend.dto.UserDto;
import com.jit.backend.entity.User;
import com.jit.backend.jwt.AuthDto;
import com.jit.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "관리자 페이지", description = "관리자 페이지 관련 api 입니다.")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    @Operation(summary = "회원조회", description = "Admin의 모든 회원 조회 기능입니다.")
    @GetMapping("/users")
    public @ResponseBody
    ResponseEntity getAllMember() {
        List<User> userList;
        try {
            userList = userService.allMember();
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @Operation(summary = "Admin 회원가입", description = "Admin의 회원가입 기능입니다.")
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid AuthDto.SignupDto signupDto) {
        String encodedPassword = encoder.encode(signupDto.getPassword());
        AuthDto.SignupDto newSignupDto = AuthDto.SignupDto.encodePassword(signupDto, encodedPassword);

        userService.registerAdmin(newSignupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "User 권한변경", description = "Admin이 User의 권한을 변경하는 기능입니다.")
    @PostMapping("/users/{userId}")
    public ResponseEntity<?> updateUserRole(@PathVariable("userId") Long userId, @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUserRole(userId, userDto));
    }
}
