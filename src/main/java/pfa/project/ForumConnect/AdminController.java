package pfa.project.ForumConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import pfa.project.ForumConnect.config.JwtService;
import pfa.project.ForumConnect.model.Post;
import pfa.project.ForumConnect.model.user;
import pfa.project.ForumConnect.repo.PostRepo;
import pfa.project.ForumConnect.repo.UserRepo;
import pfa.project.ForumConnect.service.NotificationService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private PostRepo postRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private JwtService jwtService;

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, @RequestParam String reason,@RequestHeader("Authorization") String authorizationHeader) {
        Post post = postRepository.findById(postId).orElse(null);
        String token = authorizationHeader.substring(7);


        Long userId = jwtService.extractClaim(token, claims -> claims.get("userId", Long.class));
        if (post != null) {

            Long authorId = post.getAuthorId();
            postRepository.delete(post);

            user postAuthor = userRepository.findById(post.getAuthorId()).orElse(null);

            if (postAuthor != null) {
                String notificationMessage = "Your post titled '" + post.getTitle() + "' was deleted by an admin. Reason: " + reason;
                notificationService.createNotification(userId, notificationMessage,authorId);
            }

            Map<String, String> response = new HashMap<>();
            response.put("message", "Post deleted and user notified.");
            return ResponseEntity.ok(response);        } else {
            return ResponseEntity.status(404).body("Post not found.");
        }
    }

}
