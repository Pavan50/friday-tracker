package com.bytesedge.fridaytracker.repository;

import com.bytesedge.fridaytracker.entity.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PresentationRepository extends JpaRepository<Presentation, String> {
    List<Presentation> findAllByOrderByCreatedAtDesc();
    
    @Query("SELECT p FROM Presentation p LEFT JOIN FETCH p.presenter ORDER BY p.createdAt DESC")
    List<Presentation> findAllWithPresenter();
}
