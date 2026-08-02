package com.peyman.ticketing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = {"ticket" , "message"})
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    @Column(unique = true)
    private String storedFileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;
    @CreationTimestamp
    private LocalDateTime uploadedAt;

    public Attachment(String fileName,String storedFileName, String filePath,String fileType, Long fileSize){
        this.fileName=fileName;
        this.storedFileName=storedFileName;
        this.filePath=filePath;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

}
