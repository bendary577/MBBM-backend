package com.mbbm.app.repository;

import com.mbbm.app.model.youcan.YoucanIntegration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface YoucanIntegrationRepository extends JpaRepository<YoucanIntegration, Long> {

    Optional<YoucanIntegration> findById(Long id);

}
