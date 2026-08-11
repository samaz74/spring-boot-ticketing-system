package com.peyman.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentResponse {
    private Long id;
    private String filename;
    private String fileType;
    private Long fileSize;
    private LocalDateTime uploadaAt;

}
