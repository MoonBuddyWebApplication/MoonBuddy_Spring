package com.project.moonbuddy.auth.model;

import com.project.moonbuddy.user.model.User;
import com.project.moonbuddy.user.model.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username)
                .orElseThrow(()-> new UsernameNotFoundException("등록되지 않은 회원입니다."));
        log.info("username : {}", username);
        log.info("username : {}", user.getUserId());
        return new UserPrincipal(user);
    }
}
