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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class AdminConcertController {

    private final ImageService imageService;
    private final AdminService adminService;

    @GetMapping("/admin/concert")
    public String concertUploadPage(
            HttpServletRequest servletRequest
    ) {
        Cookie[] cookies = servletRequest.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("role")) {
                String role = (cookie.getValue()).toString();

                if (!role.equals("ADMIN")) {
                    return "redirect:/";
                }
            }
        }

        return "admin/concert-upload";
    }

    @PostMapping("/admin/concert/upload")
    public String concertUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("artist") String artist,
            @RequestParam("location") String location,
            @RequestParam("reservationDay") LocalDateTime reservationDay,
            @RequestParam("startDay") LocalDateTime startDay,
            @RequestParam("endDay") LocalDateTime endDay,
            @RequestParam("link") String link,
            HttpServletRequest servletRequest
    ) throws IOException {

        ConcertUploadDto concertUploadDto = new ConcertUploadDto();
        concertUploadDto.setName(name);
        concertUploadDto.setArtist(artist);
        concertUploadDto.setLocation(location);
        concertUploadDto.setReservationDay(reservationDay);
        concertUploadDto.setStartDay(startDay);
        concertUploadDto.setEndDay(endDay);
        concertUploadDto.setLink(link);

        Cookie[] cookies = servletRequest.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("role")) {
                String role = (cookie.getValue()).toString();

                if (!role.equals("ADMIN")) {
                    return "redirect:/";
                }
            }
        }



        MultipartRequest request = (MultipartRequest) file;

        String s3Url = imageService.imageUpload(request);

        adminService.concertUpload(concertUploadDto, s3Url);

        return "redirect:/";
    }
}
