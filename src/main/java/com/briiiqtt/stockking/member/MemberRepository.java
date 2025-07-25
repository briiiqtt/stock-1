package com.briiiqtt.stockking.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);
    Member findByEmail(String email);
//    boolean save(String email, String name);
}
