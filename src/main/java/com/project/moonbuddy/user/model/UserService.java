package com.project.moonbuddy.user.model;

import com.project.moonbuddy.user.dto.CriterionDTO;
import com.project.moonbuddy.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CriterionRepository criterionRepository;

    public String create(UserDTO userDTO) {
        Criterion criterion = criterionRepository.save(Criterion.from(userDTO));
        userRepository.save(User.of(userDTO, criterion));
        return "SUCCESS";
    }

    public UserDTO read(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();
        return UserDTO.from(user);
    }

    public String update(Long userId, CriterionDTO criterionDTO) {
        Criterion criterion = criterionRepository.findByUserId(userId);
        criterionRepository.save(criterion.update(criterionDTO));
        return "SUCCESS";
    }
}
