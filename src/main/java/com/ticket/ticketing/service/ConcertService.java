package com.ticket.ticketing.service;


import com.ticket.ticketing.dto.ConcertDto;
import com.ticket.ticketing.mapper.ConcertMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertService {

    private final ConcertMapper concertMapper;

    public List<ConcertDto> getConcertList() {
        return concertMapper.findAllConcertList();
    }
}
