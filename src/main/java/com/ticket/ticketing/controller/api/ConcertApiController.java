package com.ticket.ticketing.controller.api;


import com.ticket.ticketing.dto.ConcertDto;
import com.ticket.ticketing.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/concert/api")
public class ConcertApiController {

    private final ConcertService concertService;

    @GetMapping("/concert")
    public ResponseEntity<List<ConcertDto>> reservationEvent() {
        List<ConcertDto> reservationDtos = concertService.getConcertList();

        return ResponseEntity.ok(reservationDtos);
    }
}
