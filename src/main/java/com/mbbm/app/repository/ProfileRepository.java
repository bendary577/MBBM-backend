package com.mbbm.app.repository;

import com.mbbm.app.model.base.Profile;
import com.mbbm.app.model.base.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findById(Long id);
    Optional<Profile> findByUser(User user);
}
