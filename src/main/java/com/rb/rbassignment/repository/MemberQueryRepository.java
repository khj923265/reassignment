package com.rb.rbassignment.repository;

import static com.rb.rbassignment.domain.QMember.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rb.rbassignment.domain.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Member> findByMembershipRank(String membershipRank) {
        return queryFactory.selectFrom(member)
            .where(member.membershipRank.eq(membershipRank))
            .fetch();
    }

    public void updateRefreshToken(String email, String refreshToken) {
        queryFactory.update(member)
            .where(member.email.eq(email))
            .set(member.refreshToken, refreshToken)
            .execute();
    }

}
