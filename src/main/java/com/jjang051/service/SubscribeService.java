package com.jjang051.service;

import com.jjang051.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    public void subscribe(int fromMemberId, int toMemberId) {
        //subscribeRepository.subscribe(fromMemberId, toMemberId);
        subscribeRepository.subscribe(1, 2);  // 1번 멤버가 2번 멤버를 구독
    }
}
