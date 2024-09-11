package pfa.project.ForumConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfa.project.ForumConnect.model.Reply;
import pfa.project.ForumConnect.service.ReplyService;

import java.util.List;

@RestController
@CrossOrigin (origins = "http://localhost:4200")
@RequestMapping("/api/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/add")
    public ResponseEntity<?> addReply(@RequestBody Reply repliy) {
        Reply reply= replyService.saveReply(repliy);
        return new ResponseEntity<>(reply, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reply>> getAllReply() {
        List<Reply> replies=replyService.getAllReplies();
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Reply>> getRepliesByPostId(@PathVariable Long postId) {
        List<Reply> replies = replyService.getRepliesByPostId(postId);
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReply(@PathVariable Long id) {
        replyService.deleteReply(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
