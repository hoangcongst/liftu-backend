package me.tektap.liftu.controller;

import me.tektap.liftu.Request.PostRequest;
import me.tektap.liftu.Response.PostResponse;
import me.tektap.liftu.entity.Post;
import me.tektap.liftu.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService mPostService;

    public PostController(PostService mPostService) {
        this.mPostService = mPostService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody PostRequest postRequest) {
        Post result = this.mPostService.create(postRequest);
        return ResponseEntity.ok(new PostResponse(PostResponse.SUCCESS, null));
    }
}
