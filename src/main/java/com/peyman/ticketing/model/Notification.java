package com.peyman.ticketing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.peyman.ticketing.model.enums.NotificationType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = {"user" , "ticket"})
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Boolean isRead = false;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Notification(String content, NotificationType notificationType,User user, Ticket ticket){
        this.content=content;
        this.type=notificationType;
        this.user=user;
        this.ticket=ticket;
    }


}
