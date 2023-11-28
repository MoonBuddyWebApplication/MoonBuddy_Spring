package com.project.moonbuddy.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MarkRepository extends JpaRepository<Mark,Long> {
}
