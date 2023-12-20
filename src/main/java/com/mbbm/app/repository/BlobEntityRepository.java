package com.mbbm.app.repository;

import com.mbbm.app.model.base.BlobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BlobEntityRepository extends JpaRepository<BlobEntity, Long> {

    Optional<BlobEntity> findById(Long id);

}
