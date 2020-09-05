package me.tektap.liftu.controller;

import me.tektap.liftu.Request.CommentRequest;
import me.tektap.liftu.Response.CommentResponse;
import me.tektap.liftu.Response.PostResponse.PostResponse;
import me.tektap.liftu.entity.Comment;
import me.tektap.liftu.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody CommentRequest commentRequest) {
        Comment result = this.commentService.create(commentRequest);
        return ResponseEntity.ok(new CommentResponse(PostResponse.SUCCESS, result));
    }
}
