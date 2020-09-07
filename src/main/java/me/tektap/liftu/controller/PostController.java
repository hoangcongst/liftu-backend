package me.tektap.liftu.controller;

import me.tektap.liftu.Request.PostRequest;
import me.tektap.liftu.Response.PostResponse.PostResponse;
import me.tektap.liftu.Response.PostResponse.PostIndexResponse;
import me.tektap.liftu.entity.Post.Post;
import me.tektap.liftu.entity.User;
import me.tektap.liftu.service.PostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService mPostService;

    public PostController(PostService mPostService) {
        this.mPostService = mPostService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> index(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                   @RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(new PostIndexResponse(PostResponse.SUCCESS, this.mPostService.index(pageable)));
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public ResponseEntity<?> show(@PathVariable("id") long id) {
        Optional<Post> result = this.mPostService.show(id);
        return result.map(post -> ResponseEntity.ok(new PostResponse(PostResponse.SUCCESS, post)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PostResponse(PostResponse.FAILED, null)));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody PostRequest postRequest) {
        Post result = this.mPostService.create(postRequest);
        return ResponseEntity.ok(new PostResponse(PostResponse.SUCCESS, result));
    }

    @RequestMapping(value = "{post_id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("post_id") long postId, @Valid @RequestBody PostRequest postRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Optional<Post> result = this.mPostService.show(postId);
        if(result.isPresent() && user.getId() == result.get().getUser().getId()) {
            Post mPost = this.mPostService.update(result.get(), postRequest);
            return ResponseEntity.ok(new PostResponse(PostResponse.SUCCESS, mPost));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PostResponse(PostResponse.FAILED, null));
    }
}
