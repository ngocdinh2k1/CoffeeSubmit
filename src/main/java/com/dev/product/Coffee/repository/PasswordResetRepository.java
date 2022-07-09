package com.dev.product.Coffee.repository;

import com.dev.product.Coffee.entity.PasswordResetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetEntity, Long> {
}
