package org.example.mychat.repository;

import org.example.mychat.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member getByNickname(String nickname);
}
