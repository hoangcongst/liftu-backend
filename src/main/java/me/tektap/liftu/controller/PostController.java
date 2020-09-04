package me.tektap.liftu.controller;

import me.tektap.liftu.Request.PostRequest;
import me.tektap.liftu.Response.PostCreateResponse;
import me.tektap.liftu.Response.PostIndexResponse;
import me.tektap.liftu.entity.Post.Post;
import me.tektap.liftu.service.PostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService mPostService;

    public PostController(PostService mPostService) {
        this.mPostService = mPostService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> index(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                   @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(new PostIndexResponse(PostCreateResponse.SUCCESS, this.mPostService.index(pageable)));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody PostRequest postRequest) {
        Post result = this.mPostService.create(postRequest);
        return ResponseEntity.ok(new PostCreateResponse(PostCreateResponse.SUCCESS, result));
    }
}
