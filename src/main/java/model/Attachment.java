package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.enums.TicketStatus;
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
    private String filename;
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
    private LocalDateTime upladedat;

}
