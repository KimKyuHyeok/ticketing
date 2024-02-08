package com.ticket.ticketing.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConcertUploadDto {
    private String name;
    private String artist;
    private String location;
    private String link;

    private LocalDateTime startDay;
    private LocalDateTime endDay;
    private LocalDateTime reservationDay;
}
