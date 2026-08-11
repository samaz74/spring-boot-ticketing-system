package com.peyman.ticketing.service;

import com.peyman.ticketing.repository.InvalidatedTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenCleanupService {
    private final InvalidatedTokenRepository invalidatedTokenRepository;
    public TokenCleanupService(InvalidatedTokenRepository invalidatedTokenRepository){
        this.invalidatedTokenRepository=invalidatedTokenRepository;
    }
    @Scheduled(fixedRate = 3600000)
    public void garbageCollectorJob(){
        invalidatedTokenRepository.deleteByExpiresAtBefore(LocalDateTime.now());
    }
}
