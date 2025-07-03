package com.fazbear.backend.controller;

import com.fazbear.backend.model.Animatronic;
import com.fazbear.backend.repository.AnimatronicRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animatronics")
@CrossOrigin(origins = "*")
public class AnimatronicController {

    private final AnimatronicRepository animatronicRepository;

    public AnimatronicController(AnimatronicRepository animatronicRepository) {
        this.animatronicRepository = animatronicRepository;
    }

    @GetMapping
    public ResponseEntity<List<Animatronic>> getAll() {
        return ResponseEntity.ok(animatronicRepository.findAll());
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Animatronic> getById(@PathVariable Long id) {
        Optional<Animatronic> anim = animatronicRepository.findById(id);
        return anim.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Animatronic> create(@RequestBody Animatronic animatronic) {
        Animatronic saved = animatronicRepository.save(animatronic);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animatronic> update(@PathVariable Long id, @RequestBody Animatronic details) {
        Optional<Animatronic> optional = animatronicRepository.findById(id);
        if (optional.isPresent()) {
            Animatronic anim = optional.get();
            anim.setName(details.getName());
            anim.setRole(details.getRole());
            anim.setDescription(details.getDescription());
            anim.setImageUrl(details.getImageUrl());
            return ResponseEntity.ok(animatronicRepository.save(anim));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (animatronicRepository.existsById(id)) {
            animatronicRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
