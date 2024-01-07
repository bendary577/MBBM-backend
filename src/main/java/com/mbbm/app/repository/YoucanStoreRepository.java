package com.mbbm.app.repository;

import com.mbbm.app.model.youcan.YouCanStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface YoucanStoreRepository extends JpaRepository<YouCanStore, Long> {

    Optional<YouCanStore> findById(Long id);

}
