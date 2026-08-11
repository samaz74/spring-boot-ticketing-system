package com.peyman.ticketing.controller;

import com.peyman.ticketing.dto.AttachmentResponse;

import com.peyman.ticketing.service.AttachmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {
    private final AttachmentService attachmentService;
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }
    @PostMapping("/ticket/{ticketId}")
    public void saveTicketAttachment(@PathVariable("ticketId") Long ticketId, @RequestParam("file") MultipartFile file) throws IOException {
        attachmentService.uploadAttachmentForTicket(ticketId,file);
    }
    @PostMapping("/message/{messageId}")
    public void saveMessageAttachment(@PathVariable("messageId") Long messageId, @RequestParam("file") MultipartFile file) throws IOException {
        attachmentService.uploadAtachmentForMessage(messageId,file);
    }
    @GetMapping("/ticket/{ticketId}")
    public List <AttachmentResponse> getTicketAttachment(@PathVariable("ticketId") Long ticketId){
        return attachmentService.getAttachmentsByTicket(ticketId);
    }
    @GetMapping("/message/{messageId}")
    public List <AttachmentResponse> getMessageAttachment(@PathVariable("messageId") Long messageId) {
        return attachmentService.getAttachmentsByMessageId(messageId);
    }
    @DeleteMapping("/{id}")
    public void deleteAttachment(@PathVariable("id") Long id){
        attachmentService.deleteAttachmentById(id);
    }
}
