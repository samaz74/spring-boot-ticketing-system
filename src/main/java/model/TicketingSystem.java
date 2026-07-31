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
@ToString
@NoArgsConstructor
public class TicketingSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "نام نمی تواند خالی باضد.")
    @Column(unique=true)
    private String name;
    @Column(unique=true)
    @NotBlank(message = "apiKey نمی تواند خالی باشد.")
    private String apiKey;
    private Boolean active = true;
    @CreationTimestamp
    private LocalDateTime created;

    public TicketingSystem(String name,String apiKey,Boolean active) {
        this.name = name;
        this.apiKey = apiKey;
        this.active = active;
    }


}
