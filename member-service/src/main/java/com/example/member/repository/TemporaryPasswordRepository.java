package com.example.member.repository;

import com.example.member.entity.TemporaryPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TemporaryPasswordRepository extends JpaRepository<TemporaryPassword, UUID> {
}
