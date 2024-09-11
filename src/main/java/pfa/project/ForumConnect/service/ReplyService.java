package pfa.project.ForumConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfa.project.ForumConnect.model.Reply;
import pfa.project.ForumConnect.repo.ReplyRepo;

import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepo replyRepo;

    public Reply saveReply (Reply reply) {
        return replyRepo.save(reply);
    }
    public List<Reply> getAllReplies () {
        return replyRepo.findAll();
    }
    public void deleteReply (Long id) {
        replyRepo.deleteById(id);
    }
    public List<Reply> getRepliesByPostId(Long postId) {
        return replyRepo.findByPostId(postId);
    }
}
