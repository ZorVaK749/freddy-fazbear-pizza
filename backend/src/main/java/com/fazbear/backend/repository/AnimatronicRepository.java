package com.fazbear.backend.repository;

import com.fazbear.backend.model.Animatronic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimatronicRepository extends JpaRepository<Animatronic, Long> {
}
