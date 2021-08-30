package com.rb.rbassignment.repository;

import com.rb.rbassignment.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String userEmail);

    @Modifying
    @Query("UPDATE Member m SET m.refreshToken = :refreshToken WHERE m.email = :email")
    int updateRefreshToken(String email, String refreshToken);
}
