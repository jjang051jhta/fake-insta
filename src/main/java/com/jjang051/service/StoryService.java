package com.jjang051.service;

import com.jjang051.dto.CustomUserDetails;
import com.jjang051.dto.StoryUploadDto;
import com.jjang051.entity.Story;
import com.jjang051.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoryService {
    @Value("${file.path}")
    private String uploadFolder;

    private final StoryRepository storyRepository;
    public void upload(StoryUploadDto storyUploadDto, CustomUserDetails customUserDetails) {
        String originalFileName = storyUploadDto.getFile().getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String imageFileName= uuid+"_"+originalFileName;
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
        try {
            Files.write(imageFilePath, storyUploadDto.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Story story= storyUploadDto.toEntity(imageFileName,customUserDetails.getLoggedMember());
        storyRepository.save(story);
    }
}
