package com.example.member.repository;

import com.example.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID>, QuerydslPredicateExecutor<Member> {

    Optional<Member> findOneByEmail(String email);
}
