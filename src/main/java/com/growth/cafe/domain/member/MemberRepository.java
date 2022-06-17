package com.growth.cafe.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	Member findByUsername(String username);
	boolean existsByUsername(String username);
}
