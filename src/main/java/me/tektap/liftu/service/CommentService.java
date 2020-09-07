package me.tektap.liftu.service;

import me.tektap.liftu.Request.CommentCreateRequest;
import me.tektap.liftu.dao.CommentRepository;
import me.tektap.liftu.dao.PostRepository;
import me.tektap.liftu.entity.Comment;
import me.tektap.liftu.entity.Post.Post;
import me.tektap.liftu.exception.AuthorizeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment create(CommentCreateRequest commentRequest) {
        Comment comment = new Comment(commentRequest.getPost_id(), commentRequest.getContent(), commentRequest.getParent_comment_id());
        this.changeNumberParentComment(comment.getParentCommentId(), 1);
        this.changeNumberCommentOfPost(comment.getPostId(), 1);
        return this.commentRepository.save(comment);
    }

    public boolean delete(long commentId, long userId) {
        Optional<Comment> commentOptional = this.commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            if (comment.getUser().getId() != userId)
                throw new AuthorizeException("CommentService delete post not allowed", new Exception());
            if (comment.getStatus() == Comment.HIDDEN)
                return false;
            if (comment.getParentCommentId() != 0)
                this.changeNumberParentComment(comment.getParentCommentId(), -1);
            this.changeNumberCommentOfPost(comment.getPostId(), -1);
            comment.setStatus(Comment.HIDDEN);
            this.commentRepository.save(comment);
            return true;
        }
        return false;
    }

    public Page<Comment> index(long postId, long parentCommentId, Pageable pageable) {
        return this.commentRepository.findAllByPostIdAndStatusAndParentCommentIdOrderByIdDesc(postId, Comment.VISIBLE, parentCommentId, pageable);
    }

    public Comment update(long commentId, long userId, String content) {
        Optional<Comment> result = this.commentRepository.findById(commentId);
        if (result.isPresent() && result.get().getUser().getId() == userId) {
            Comment comment = result.get();
            comment.setContent(content);
            this.commentRepository.save(comment);
            return comment;
        } else throw new AuthorizeException("CommentService update post not allowed", new Exception());
    }

    private void changeNumberParentComment(long parentCommentId, int value) {
        if (parentCommentId != 0) {
            Optional<Comment> parentCommentResult = this.commentRepository.findById(parentCommentId);
            if (parentCommentResult.isPresent()) {
                Comment parentComment = parentCommentResult.get();
                parentComment.setNumberChildComment(parentComment.getNumberChildComment() + value);
                this.commentRepository.save(parentComment);
            }
        }
    }

    private void changeNumberCommentOfPost(long postId, int value) {
        Optional<Post> postResult = this.postRepository.findById(postId);
        if (postResult.isPresent()) {
            Post post = postResult.get();
            post.setNumberComment(post.getNumberComment() + value);
            this.postRepository.save(post);
        }
    }
}
