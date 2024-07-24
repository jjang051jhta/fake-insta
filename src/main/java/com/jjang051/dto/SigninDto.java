package com.jjang051.dto;

import com.jjang051.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SigninDto {
    @Size(min=3,max=20,message = "3글자 이상 20글자 이하로 써주세요")
    @NotBlank(message = "필수입력사항입니다.")
    private String userId;

    @Size(min=3,max=20,message = "3글자 이상 20글자 이하로 써주세요")
    @NotBlank(message = "필수입력사항입니다.")
    private String userName;

    @NotBlank(message = "필수입력사항입니다.")
    private String password;

    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    private String email;

    @Builder
    public SigninDto(String userId, String userName, String password, String email) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    // 시큐리티 적용해서 패스워드 암호화해서 올려보기

    public static Member toEntity(SigninDto signinDto) {
        return Member.builder()
                .userId(signinDto.getUserId())
                .userName(signinDto.getUserName())
                .password(signinDto.getPassword())
                .email(signinDto.getEmail())
                .build();
    }
}
