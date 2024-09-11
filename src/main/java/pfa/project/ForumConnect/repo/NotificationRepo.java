package pfa.project.ForumConnect.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.project.ForumConnect.model.Notification;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndStatus(Long userId, String status);

}
