package com.ticket.ticketing.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.ticket.ticketing.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/image")
@Controller
public class ImageController {

    private String uploadPath;
    private ImageService imageService;

    @Autowired
    private AmazonS3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/{imageName}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String imageName) {
        try {
            // S3 버킷에서 이미지 객체를 가져옵니다.
            S3Object s3Object = s3Client.getObject(bucket, imageName);

            // 이미지 객체에서 InputStream을 가져옵니다.
            S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
            InputStreamResource inputStreamResource = new InputStreamResource(objectInputStream);

            // 이미지를 응답으로 반환합니다.
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG) // 기본값은 PNG로 설정되어 있습니다.
                    .body(inputStreamResource);
        } catch (Exception e) {
            // 이미지가 존재하지 않는 경우 404 응답을 반환합니다.
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PostMapping("/upload")
    public Map<String, Object> uploadImages(MultipartRequest request) throws Exception {

        Map<String, Object> responseData = new HashMap<>();

        try {

            String s3Url = imageService.imageUpload(request);

            responseData.put("uploaded", true);
            responseData.put("url", s3Url);


            return responseData;

        } catch (IOException e) {

            responseData.put("uploaded", false);

            return responseData;
        }
    }
}
