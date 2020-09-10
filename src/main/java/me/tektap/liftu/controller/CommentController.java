package me.tektap.liftu.controller;

import me.tektap.liftu.Request.CommentCreateRequest;
import me.tektap.liftu.Request.CommentUpdateRequest;
import me.tektap.liftu.Response.CommentResponse;
import me.tektap.liftu.Response.PostResponse.PostResponse;
import me.tektap.liftu.entity.Comment;
import me.tektap.liftu.entity.User.User;
import me.tektap.liftu.exception.AuthorizeException;
import me.tektap.liftu.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RepositoryRestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid CommentCreateRequest commentRequest) {
        Comment result = this.commentService.create(commentRequest);
        return ResponseEntity.ok(new CommentResponse(PostResponse.SUCCESS, result));
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public @ResponseBody
    ResponseEntity<?> index(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                            @RequestParam(name = "post_id") Integer postId,
                            @RequestParam(name = "parent_comment_id", required = false, defaultValue = "0") Integer parentCommentId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> producers = this.commentService.index(postId, parentCommentId, pageable);
        return ResponseEntity.ok(producers);
    }

    @RequestMapping(value = "{comment_id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable("comment_id") long commentId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        try {
            if (this.commentService.delete(commentId, user.getId()))
                return ResponseEntity.ok(new CommentResponse(CommentResponse.SUCCESS, null));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommentResponse(CommentResponse.FAILED, null));
        } catch (AuthorizeException e) {
            CommentResponse commentResponse = new CommentResponse(CommentResponse.FAILED, null);
            commentResponse.setMessage("Cannot delete another user's comment!!!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commentResponse);
        }
    }

    @RequestMapping(value = "{comment_id}", method = RequestMethod.PUT)
    ResponseEntity<?> update(@PathVariable("comment_id") long commentId,
                             @RequestBody @Valid CommentUpdateRequest commentUpdateRequest,
                             Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        try {
            return ResponseEntity.ok(new CommentResponse(CommentResponse.SUCCESS,
                    this.commentService.update(commentId,
                            user.getId(), commentUpdateRequest.getContent())));
        } catch (AuthorizeException e) {
            CommentResponse commentResponse = new CommentResponse(CommentResponse.FAILED, null);
            commentResponse.setMessage("Cannot edit another user's comment!!!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commentResponse);
        }
    }
}
