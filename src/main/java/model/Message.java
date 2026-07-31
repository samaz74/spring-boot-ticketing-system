package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"ticket", "sentBy"})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "پیام نبایست خالی باشد.")
    private String content;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    @ManyToOne
    @JoinColumn(name = "sentBy_id")
    private User sentBy;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Message(String content, Ticket ticket, User sentBy) {
        this.content = content;
        this.ticket = ticket;
        this.sentBy = sentBy;
    }
}
