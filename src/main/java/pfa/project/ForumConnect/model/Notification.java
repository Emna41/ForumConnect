package pfa.project.ForumConnect.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "user_id", nullable = false)
    private Long user;

    @Column(nullable = false)
    private String message;

    @Column(name = "author_ID", nullable = false)
    private Long authorId;


    @Column(name = "status", nullable = false)
    private String status = "unread";

    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt = LocalDateTime.now();
}
