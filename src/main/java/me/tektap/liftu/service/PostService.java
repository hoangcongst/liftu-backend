package me.tektap.liftu.service;

import me.tektap.liftu.Request.PostRequest;
import me.tektap.liftu.dao.PostRepository;
import me.tektap.liftu.entity.Post.Post;
import me.tektap.liftu.entity.Post.PostAtIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private final PostRepository mPostRepository;

    public PostService(PostRepository mPostRepository) {
        this.mPostRepository = mPostRepository;
    }

    public Post create(PostRequest postRequest) {
        Post mPost = new Post(postRequest.getTitle(), postRequest.getDescription(),
                postRequest.getContent(), postRequest.getStatus());
        return this.mPostRepository.save(mPost);
    }

    public Page<PostAtIndex> index(Pageable pageable) {
        return this.mPostRepository.findAllByStatus(Post.PUBLISH, PostAtIndex.class, pageable);
    }

    public Optional<Post> show(long id) {
        return this.mPostRepository.findById(id);
    }

    public Post update(Post originalPost, PostRequest mPostRequest) {
        originalPost.setContent(mPostRequest.getContent());
        originalPost.setDescription(mPostRequest.getDescription());
        originalPost.setTitle(mPostRequest.getTitle());
        originalPost.setStatus(mPostRequest.getStatus());
        return this.mPostRepository.save(originalPost);
    }
}
