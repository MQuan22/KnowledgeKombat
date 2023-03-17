package com.example.knowledgekombat.repository;

import com.example.knowledgekombat.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface UniRepository extends JpaRepository<University,Long> {
    @Query("SELECT u from University u where u.name = ?1")
    Optional<University> findByName(String name);
}
