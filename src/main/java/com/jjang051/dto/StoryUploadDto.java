package com.jjang051.dto;

import com.jjang051.entity.Member;
import com.jjang051.entity.Story;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class StoryUploadDto {
    private MultipartFile file;
    private String caption;

    public Story toEntity(String imgUrl, Member member) {
        return Story.builder()
                .caption(caption)
                .imgUrl(imgUrl)
                .member(member)
                .build();
    }
}
