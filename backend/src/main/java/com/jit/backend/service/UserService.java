package com.jit.backend.service;

import com.jit.backend.entity.User;
import com.jit.backend.jwt.AuthDto;
import com.jit.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void registerUser(AuthDto.SignupDto signupDto) {
        validateDuplicateMember(signupDto.getEmail());
        User user = User.registerUser(signupDto);
        userRepository.save(user);
    }
    @Transactional
    public void registerAdmin(AuthDto.SignupDto signupDto) {
        validateDuplicateMember(signupDto.getEmail());
        User user = User.registerAdmin(signupDto);
        userRepository.save(user);
    }

    private void validateDuplicateMember(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    public List<User> allMember()  {
        return userRepository.findAll();
    }



}
