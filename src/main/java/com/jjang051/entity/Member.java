package com.jjang051.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jjang051.constant.Role;
import com.jjang051.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //jpa에서 가져다 쓸때 필요함
@ToString(exclude = {"writer"})
//@ToString(of = {"writer"})
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    private String mbti;

    private String description;

    private String profileImageUrl;

    @Column(name="role")
    @Enumerated(EnumType.STRING)  //Role은 상수이다.
    private Role role;

    //연관관계 설정
    @OneToMany(mappedBy = "writer")
    private List<Comment> comments;

    //api
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"member"}) //api만들때 양방향매핑이 잡혀있을때 서로 참조 하다보면 무한루프에 빠지게 된다.
    private List<Story> stories;




    @Builder
    public Member(Integer id,String userId, String userName, String password, String email, String mbti, String description, String profileImageUrl, Role role, List<Comment> comments) {
        this.id=id;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mbti = mbti;
        this.description = description;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
        this.comments = comments;
    }


    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .email(member.getEmail())
                .comments(member.getComments())
                .stories(member.getStories())
                .description(member.getDescription())
                .profileImageUrl(member.getProfileImageUrl())
                .mbti(member.getMbti())
                .build();
    }
    //setter  속성
    public void updateMember(MemberDto memberDto) {
        //this.userId = memberDto.getUserId();
        this.userName = memberDto.getUserName();
        //this.password = memberDto.getPassword();
        this.email = memberDto.getEmail();
        this.mbti = mbti;
        this.description = memberDto.getDescription();
        this.profileImageUrl = memberDto.getProfileImageUrl();
        this.role = memberDto.getRole();
        this.comments = memberDto.getComments();
    }
}
