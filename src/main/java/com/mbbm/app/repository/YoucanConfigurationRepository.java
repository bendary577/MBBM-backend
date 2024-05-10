package com.mbbm.app.repository;

import com.mbbm.app.model.youcan.YoucanConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface YoucanConfigurationRepository extends JpaRepository<YoucanConfiguration, Long> {

    Optional<YoucanConfiguration> findById(Long id);

}
