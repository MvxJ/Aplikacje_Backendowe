package com.gymworkouts.gymworkouts.Repository;

import com.gymworkouts.gymworkouts.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findById(long categoryId);
}
