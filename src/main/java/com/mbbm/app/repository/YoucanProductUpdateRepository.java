package com.mbbm.app.repository;

import com.mbbm.app.model.youcan.YoucanProductUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface YoucanProductUpdateRepository extends JpaRepository<YoucanProductUpdate, Long> {

    Optional<YoucanProductUpdate> findById(Long id);

}
