package com.project.moonbuddy.user.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CriterionRepository extends JpaRepository<Criterion, Long> {
    Criterion findByUserId(Long userId);
}
