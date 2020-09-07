package me.tektap.liftu.controller;

import me.tektap.liftu.Response.StorageResponse;
import me.tektap.liftu.annotation.ValidImage;
import me.tektap.liftu.service.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/storage")
public class StorageController {

    private AmazonClient amazonClient;

    @Autowired
    StorageController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("")
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file") @Valid @ValidImage MultipartFile file) {
        return ResponseEntity.ok(new StorageResponse(StorageResponse.SUCCESS, this.amazonClient.uploadFile(file)));
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
}