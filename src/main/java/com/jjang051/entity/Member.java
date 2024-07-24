package com.jjang051.entity;

import com.jjang051.constant.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //jpa에서 가져다 쓸때 필요함
@ToString(exclude = {"writer"})
//@ToString(of = {"writer"})
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

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

}
