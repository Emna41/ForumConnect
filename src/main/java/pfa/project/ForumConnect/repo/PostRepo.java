package pfa.project.ForumConnect.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.project.ForumConnect.model.Post;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findByAuthorId(Long authorId);
}
