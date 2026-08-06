package com.peyman.ticketing.service;

import com.peyman.ticketing.model.Attachment;
import com.peyman.ticketing.repository.AttachmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final TicketService ticketService;
    private final MessageService messageService;
    public AttachmentService(AttachmentRepository attachmentRepository, TicketService ticketService, MessageService messageService) {
        this.attachmentRepository = attachmentRepository;
        this.ticketService = ticketService;
        this.messageService = messageService;
    }
    public void uploadAttachmentForTicket(Long ticketId, MultipartFile file) throws IOException {
        Attachment attachment = new Attachment();
        attachment.setTicket(ticketService.getEntityById(ticketId));
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setFileSize(file.getSize());
        String storedName = UUID.randomUUID().toString();
        attachment.setStoredFileName(storedName + "_" + file.getOriginalFilename());
        attachment.setFilePath("uploads/" + storedName + "_" + file.getOriginalFilename());
        Path uploadPath = Path.of("uploads/");
        Files.createDirectories(uploadPath);
        Files.copy(file.getInputStream(), Path.of("uploads/" + storedName));
        attachmentRepository.save(attachment);
    }
    public void uploadAtachmentForMessage(Long messageId, MultipartFile file) throws IOException {
        Attachment attachment = new Attachment();
        attachment.setMessage(messageService.getEntityMessageById(messageId));
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setFileSize(file.getSize());
        String storedName = UUID.randomUUID().toString();
        attachment.setStoredFileName(storedName + "_" + file.getOriginalFilename());
        attachment.setFilePath("uploads/" + storedName + "_" + file.getOriginalFilename());
        Path uploadPath = Path.of("uploads/");
        Files.createDirectories(uploadPath);
        Files.copy(file.getInputStream(), Path.of("uploads/" + storedName));
        attachmentRepository.save(attachment);
    }
    public List<Attachment> getAttachmentsByTicket(Long ticketId) {
        return attachmentRepository.getAttachmentByTicket_Id(ticketId);
    }
    public List<Attachment> getAttachmentsByMessageId(Long messageId) {
        return attachmentRepository.getAttachmentByMessage_Id(messageId);
    }
    public void deleteAttachmentById(Long attachmentId) {
        attachmentRepository.deleteById(attachmentId);
    }

}
