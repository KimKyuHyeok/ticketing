package com.ticket.ticketing.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    @Value("/")
    private String uploadPath;

    public String imageUpload(MultipartRequest request) throws IOException {

        MultipartFile file = request.getFile("upload");

        // 이미지 파일의 확장자를 가져오기
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        // 새로운 파일명 생성
        String fileName = UUID.randomUUID().toString() + "." + extension;

        // 파일을 저장할 경로 지정
        Path filePath = Paths.get(uploadPath, fileName);

        // 파일 저장
        Files.write(filePath, file.getBytes());

        return fileName;
    }
}
