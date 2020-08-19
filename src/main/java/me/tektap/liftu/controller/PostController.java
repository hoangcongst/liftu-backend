package me.tektap.liftu.controller;

import me.tektap.liftu.service.PostService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${site.crossorigin}")
public class PostController {
    private PostService mPostService;

    public PostController(PostService mPostService) {
        this.mPostService = mPostService;
    }
}
