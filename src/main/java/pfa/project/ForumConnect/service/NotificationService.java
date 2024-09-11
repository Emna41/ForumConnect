package pfa.project.ForumConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfa.project.ForumConnect.model.Notification;
import pfa.project.ForumConnect.repo.NotificationRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepository;

    public void createNotification(Long user, String message, Long authorId) {
        Notification notification = Notification.builder()
                .user(user)
                .message(message)
                .status("unread")
                .authorId(authorId)
                .createdAt(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserAndStatus(userId, "unread");
    }

    public void markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setStatus("read");
        notificationRepository.save(notification);
    }
}

