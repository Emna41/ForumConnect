package pfa.project.ForumConnect.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String author;

    @Column(name="author_id")
    private Long authorId;

    @Column(name="date_created")
    private String dateCreated;

    private int replies;




}

