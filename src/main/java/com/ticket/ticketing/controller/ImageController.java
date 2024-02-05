package com.ticket.ticketing.controller;

import com.ticket.ticketing.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

@RequiredArgsConstructor
@RequestMapping("/image")
@Controller
public class ImageController {
    @Value("${upload.path}")
    private String uploadPath;
    private final ImageService imageService;

    @ResponseBody
    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {

        Resource resource = new FileSystemResource(uploadPath + "/" + imageName);

        if (!resource.exists())
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);

        HttpHeaders headers = new HttpHeaders();
        Path filePath = null;

        try {
            filePath = Paths.get(uploadPath + "/" + imageName);

            headers.add("Content-Type", Files.probeContentType(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/upload")
    public Map<String, Object> uploadImages(MultipartRequest request) {

        Map<String, Object> responseData = new HashMap<>();

        try {

            String imageUrl = imageService.imageUpload(request);

            responseData.put("uploaded", true);
            responseData.put("url", imageUrl);

            return responseData;

        } catch (IOException e) {

            responseData.put("uploaded", false);

            return responseData;
        }
    }
}
