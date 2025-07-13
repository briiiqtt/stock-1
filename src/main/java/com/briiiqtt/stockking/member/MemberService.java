package com.briiiqtt.stockking.member;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    @Nullable
    Member getMemberById(Long id);
    Member getMemberByEmail(String email);
    Member register(Member member);
}
