package com.gty.gtyserverapi.repository;


import com.gty.gtyserverapi.model.category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<category,Long> {
}
