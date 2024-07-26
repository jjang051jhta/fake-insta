package com.jjang051.dto;

import com.jjang051.constant.Role;
import com.jjang051.entity.Comment;
import com.jjang051.entity.Member;
import com.jjang051.entity.Story;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Integer id;
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String mbti;
    private String description;
    private String profileImageUrl;
    private Role role;
    private List<Comment> comments;
    private List<Story> stories;
}
