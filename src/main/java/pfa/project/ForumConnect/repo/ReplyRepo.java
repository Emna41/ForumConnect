package pfa.project.ForumConnect.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.project.ForumConnect.model.Reply;

import java.util.List;

public interface ReplyRepo extends JpaRepository<Reply, Long> {
    int countByPostId(Long postId);
    List<Reply> findByPostId(Long postId);
}
