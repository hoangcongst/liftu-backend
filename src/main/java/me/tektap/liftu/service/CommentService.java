package me.tektap.liftu.service;

import me.tektap.liftu.Request.CommentRequest;
import me.tektap.liftu.dao.CommentRepository;
import me.tektap.liftu.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment create(CommentRequest commentRequest) {
        Comment comment = new Comment(commentRequest.getPost_id(),
                commentRequest.getContent(), commentRequest.getParent_comment_id());
        return this.commentRepository.save(comment);
    }

//    public Page<Comment> index(long postId) {
//        return this.commentRepository.findAll(pageable);
//    }
}
