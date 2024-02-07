package com.ticket.ticketing.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ticket.ticketing.config.S3Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private S3Config s3Config;

    @Value("/Users/kyuhyeokmac/Desktop/StudyCode/ticketing/images")
    private String uploadPath;

    @Autowired
    public ImageService(S3Config s3Config) {
        this.s3Config = s3Config;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String imageUpload(MultipartRequest request) throws IOException {

        MultipartFile file = request.getFile("upload");

        // 이미지 파일의 확장자를 가져오기
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        // 새로운 파일명 생성
        String fileName = UUID.randomUUID().toString() + "." + extension;

        // 파일을 저장할 경로 지정
        String localPath = uploadPath + fileName;

        // 파일 저장
        File localFile = new File(localPath);
        file.transferTo(localFile);

        //s3 이미지 업로드
        s3Config.amazonS3Client().putObject(new PutObjectRequest(bucket, fileName, localFile).withCannedAcl(CannedAccessControlList.PublicRead));
        String s3Url = s3Config.amazonS3Client().getUrl(bucket, fileName).toString();

        System.out.println("URL >>>>>>" + s3Url);

        //서버에 저장한 이미지 삭제
        localFile.delete();

        return s3Url;
    }
}
