package com.jjang051.repository;

import com.jjang051.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    //crud여기서 해결가능...
}
