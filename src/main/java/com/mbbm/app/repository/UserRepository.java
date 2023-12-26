package com.mbbm.app.repository;

import com.mbbm.app.model.base.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findById(Long id);
    User findByEmail(String email);
    User findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
