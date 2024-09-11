package pfa.project.ForumConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pfa.project.ForumConnect.model.Notification;
import pfa.project.ForumConnect.service.NotificationService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getUserNotifications(@RequestParam Long userId) {
        return notificationService.getUserNotifications(userId);
    }

    @PutMapping("/{id}")
    public void markAsRead(@PathVariable Long id) {
        notificationService.markNotificationAsRead(id);
    }
}
