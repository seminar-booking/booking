package com.example.member.log.repository;

import com.example.member.log.entity.ApiRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApiRequestRepository extends JpaRepository<ApiRequest, UUID>, QuerydslPredicateExecutor<ApiRequest> {
}