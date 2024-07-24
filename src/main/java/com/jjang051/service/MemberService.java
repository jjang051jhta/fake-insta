package com.jjang051.service;

import com.jjang051.dto.SigninDto;
import com.jjang051.entity.Member;
import com.jjang051.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member singin(SigninDto signinDto) {
        Member returnMember = memberRepository.save(SigninDto.toEntity(signinDto)); //
        return returnMember;
    }
}
