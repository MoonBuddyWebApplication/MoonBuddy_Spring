package com.project.moonbuddy.user.model;

import com.project.moonbuddy.auth.model.UserPrincipal;
import com.project.moonbuddy.user.dto.CriterionDTO;
import com.project.moonbuddy.user.dto.UserDTO;
import com.project.moonbuddy.user.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CriterionRepository criterionRepository;
    private final PasswordEncoder passwordEncoder;


    public String create(UserDTO userDTO) {
        Criterion criterion = criterionRepository.save(Criterion.from(userDTO));
        String pass = passwordEncoder.encode(userDTO.getPw());
        userRepository.save(User.of(userDTO, pass, criterion));
        return "SUCCESS";
    }

    public UserDTO read(UserPrincipal loginUser) {
        User user = userRepository.findById(getLoginUserId(loginUser))
                .orElseThrow();
        return UserDTO.from(user);
    }

    public String updateCriterion(UserPrincipal loginUser, CriterionDTO criterionDTO) {
        Criterion criterion = criterionRepository.findByUserId(getLoginUserId(loginUser));
        criterionRepository.save(criterion.update(criterionDTO));
        return "SUCCESS";
    }

    public String update(UserPrincipal loginUser, UserInfoDTO userInfoDTO) {
        User user = userRepository.findById(getLoginUserId(loginUser))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다"));
        String pass = passwordEncoder.encode(userInfoDTO.getPw());
        userRepository.save(user.update(userInfoDTO, pass));
        return "SUCCESS";
    }

    public Long getLoginUserId(UserPrincipal loginUser) {
        if (loginUser == null) {
            return 13L;
        }
        return loginUser.getId();
    }


}
