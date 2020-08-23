package me.tektap.liftu.service;

import me.tektap.liftu.Request.PostRequest;
import me.tektap.liftu.dao.PostRepository;
import me.tektap.liftu.entity.Post;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private PostRepository mPostRepository;

    public PostService(PostRepository mPostRepository) {
        this.mPostRepository = mPostRepository;
    }

    public Post create(PostRequest postRequest) {
        Post mPost = new Post(postRequest.getTitle(), postRequest.getAlias(),
                postRequest.getContent(), postRequest.getStatus());
        return this.mPostRepository.savePost(mPost);
    }
}
