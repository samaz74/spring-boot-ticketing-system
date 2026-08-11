package com.peyman.ticketing.dto.mapper;

import com.peyman.ticketing.dto.AttachmentResponse;
import com.peyman.ticketing.model.Attachment;

public class AttachmentMapper {
    public static AttachmentResponse toDto(Attachment attachment){
        return new AttachmentResponse(attachment.getId(),attachment.getFileName(),attachment.getFileType(),attachment.getFileSize(),attachment.getUploadedAt());
    }
}
