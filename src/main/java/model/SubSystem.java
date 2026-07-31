package model;

import jakarta.annotation.Nullable;
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
@ToString(exclude = "ticketingSystem")
@NoArgsConstructor
public class SubSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    @NotBlank(message = "نام نمی تواند خالی باشد")
    private String name;
    private String description;
    @NotBlank(message = "شناسه سیستم نمی تواند خالی باشد")
    private String prefix;
    private int ticketCounter;
    private Boolean isActive= true;
    @CreationTimestamp
    private LocalDateTime created ;
    @ManyToOne
    @JoinColumn(name = "ticketingSystem_id")
    private TicketingSystem ticketingSystem;

    public SubSystem (String name,String description,String prefix,Boolean active,TicketingSystem ticketingSystem) {
        this.name = name;
        this.description = description;
        this.prefix = prefix;
        this.isActive = active;
        this.ticketCounter = 0;
        this.ticketingSystem = ticketingSystem;
    }
}
