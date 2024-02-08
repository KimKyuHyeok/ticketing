package com.ticket.ticketing.controller.admin;

import com.ticket.ticketing.dto.request.ConcertUploadDto;
import com.ticket.ticketing.service.ImageService;
import com.ticket.ticketing.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class AdminConcertController {

    private final ImageService imageService;
    private final AdminService adminService;

    @GetMapping("/admin/concert")
    public String concertUploadPage() {

        return "admin/concert-upload";
    }

    @PostMapping("/admin/concert/upload")
    public String concertUpload(
            @RequestBody ConcertUploadDto concertUploadDto,
            @RequestParam(required = false) MultipartFile image
    ) throws IOException {

        MultipartRequest request = (MultipartRequest) image;

        String s3Url = imageService.imageUpload(request);

        adminService.concertUpload(concertUploadDto, s3Url);

        return "redirect:/";
    }
}
