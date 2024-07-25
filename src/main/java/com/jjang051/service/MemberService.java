package com.jjang051.service;

import com.jjang051.dto.MemberDto;
import com.jjang051.dto.SigninDto;
import com.jjang051.entity.Member;
import com.jjang051.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.path}")
    private String uploadFolder;

    public Member singin(SigninDto signinDto) {
        signinDto.setPassword(bCryptPasswordEncoder.encode(signinDto.getPassword()));
        Member returnMember = memberRepository.save(SigninDto.toEntity(signinDto)); //
        return returnMember;
    }

    //응답용 dto,
    public MemberDto getInfoMember(String userId) {
        Optional<Member> optionalMember =
                memberRepository.findByUserId(userId);
        if(optionalMember.isPresent()) {
          return Member.fromEntity(optionalMember.get());
        }
        return null;
    }

    public Member modifyMemberSetter(MemberDto memberDto, String userId) {

        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        log.info("userId==={}",userId);
        if(optionalMember.isPresent()) {
            Member findMember = optionalMember.get();


            findMember.setMbti(memberDto.getMbti());
            findMember.setEmail(memberDto.getEmail());
            findMember.setDescription(memberDto.getDescription());
            findMember.setUserName(memberDto.getUserName());
            return memberRepository.save(findMember);
        } else {
            throw new RuntimeException("찾는 사람이 없습니다.");
        }
    }
    public Member modifyMemberBuilder(MemberDto memberDto, String userId) {

        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        log.info("userId==={}",userId);
        if(optionalMember.isPresent()) {
            Member findMember = optionalMember.get();
            memberRepository.save(findMember);
            Member saveMember =
                    Member.builder()
                    .id(findMember.getId())
                    .userId(findMember.getUserId())
                    .userName(memberDto.getUserName())
                    .password(findMember.getPassword())
                    .mbti(memberDto.getMbti())
                    .email(memberDto.getEmail())
                    .description(memberDto.getDescription())
                    .build();


            return memberRepository.save(saveMember);
        } else {
            throw new RuntimeException("찾는 사람이 없습니다.");
        }
    }

    public Member modifyMember(MemberDto memberDto, String userId) {
        // update
        // 1. id를 가지고 찾는다.
        // 2. 속성을 바꾸고
        // 3. save  update
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        log.info("userId==={}",userId);
        if(optionalMember.isPresent()) {
            Member findMember = optionalMember.get();
            findMember.updateMember(memberDto); //속성을 바꿔준다.
            return memberRepository.save(findMember);  //update가 나간다.
        } else {
            throw new RuntimeException("찾는 사람이 없습니다.");
        }
    }

    //@Transactional
    public Member changeProfileImage(String userId,MultipartFile profileImageUrl) {

        String originalFileName = profileImageUrl.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+profileImageUrl.getOriginalFilename();
        log.info("imageFileName===={}",imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
        File originalFile = new File(uploadFolder+imageFileName);
        try {
            Files.write(imageFilePath,profileImageUrl.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        if(optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setProfileImageUrl(imageFileName);
            return memberRepository.save(member);

        }
        throw new RuntimeException("파일업로드에 실패하였습니다.");
    }
}
