package com.mbbm.app.repository;

import com.mbbm.app.enums.ESubscriptionType;
import com.mbbm.app.model.base.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findById(Long id);
    Optional<Subscription> findByName(ESubscriptionType name);
}
