package com.peyman.ticketing.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.peyman.ticketing.model.enums.Priority;
import com.peyman.ticketing.model.enums.TicketStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = {"subSystem", "createdByUser", "assignedTo"})
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String ticketNumber;
    @NotBlank(message = "عنوان نباید خالی باشد.")
    private String title;
    @NotBlank(message = "توضیحات نباید خالی باشد.")
    private String description;
    @Enumerated(EnumType.STRING)
    private TicketStatus status = TicketStatus.OPEN;
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;
    @ManyToOne
    @JoinColumn(name= "subSystem_id")
    private SubSystem subSystem;
    @ManyToOne
    @JoinColumn(name = "createdByUser_id")
    private User createdByUser;
    @ManyToOne
    @JoinColumn(name = "assignedTo_id")
    private User assignedTo;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Ticket (String title, String description, Priority priority){
        this.title = title;
        this.description = description;
        this.priority = priority;
    }


}
