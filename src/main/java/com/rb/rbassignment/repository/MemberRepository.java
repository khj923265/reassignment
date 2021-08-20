package com.rb.rbassignment.repository;

import com.rb.rbassignment.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    int save(Member member);
}
