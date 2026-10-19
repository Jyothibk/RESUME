package com.palle.repository;

import com.palle.model.model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repository extends JpaRepository<model, Long> {
    // Make sure it says "interface" above, not "class"
}