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
@ToString(exclude = {"subSystem",  "user"})
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"User_id","SubSystem_id"}))
public class SupportAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;
    @ManyToOne
    @JoinColumn(name= "SubSystem_id")
    private SubSystem subSystem;
    @CreationTimestamp
    private LocalDateTime created;

    public SupportAccess(User user, SubSystem subSystem) {
        this.user = user;
        this.subSystem = subSystem;
    }
}
