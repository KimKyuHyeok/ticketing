package com.ticket.ticketing.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConcertDto {
    private Long concertId;
    private String artist;
    private String name;
    private String location;
    private String link;
    private LocalDateTime startDay;
    private LocalDateTime endDay;
    private LocalDateTime reservationDay;
    private String image;
}
