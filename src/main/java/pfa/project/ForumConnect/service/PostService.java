package pfa.project.ForumConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfa.project.ForumConnect.model.Post;
import pfa.project.ForumConnect.repo.PostRepo;
import pfa.project.ForumConnect.repo.ReplyRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ReplyRepo replyRepo;

    public Post savePost(Post post) {
        return postRepo.save(post);
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        for (Post post : posts) {
            int replyCount = replyRepo.countByPostId(post.getId());
            post.setReplies(replyCount);
        }
        return posts;
    }

    public Optional<Post> getPostById(Long id) {
        return postRepo.findById(id);
    }

    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

    public List<Post> getPostsByAuthorId(Long authorId) {
        List<Post> posts = postRepo.findByAuthorId(authorId);
        for (Post post : posts) {
            int replyCount = replyRepo.countByPostId(post.getId());
            post.setReplies(replyCount);
        }
        return posts;
    }
}
