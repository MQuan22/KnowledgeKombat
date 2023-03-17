package com.example.knowledgekombat.repository;

import com.example.knowledgekombat.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT c from Category c where c.name = ?1")
    Optional<Category> findByName(String name);
}
