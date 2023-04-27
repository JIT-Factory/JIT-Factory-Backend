package com.jit.backend.service;

import com.jit.backend.dto.RoleDto;
import com.jit.backend.entity.User;
import com.jit.backend.authorize.jwt.AuthDto;
import com.jit.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public User updateUserRole(Long id, RoleDto roleDto){
        User user = userRepository.findById(id).orElseThrow(()->{
                throw new IllegalStateException("User 정보를 다시 확인하세요");});
        user.changeUserRole(roleDto);
        userRepository.saveAndFlush(user);
        return user;
    }

    public String currentUserInfo(String email){
        User user = userRepository.findByEmail(email).orElseThrow();
        return user.getFactoryName();
    }
}
