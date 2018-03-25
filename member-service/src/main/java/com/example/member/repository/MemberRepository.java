package com.example.member.repository;

import com.example.member.entity.Member;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID>, QuerydslPredicateExecutor<Member>, RevisionRepository<Member, UUID, Integer> {

    Optional<Member> findOneByEmail(String email);
}
