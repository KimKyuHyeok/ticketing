package com.ticket.ticketing.mapper;

import com.ticket.ticketing.dto.ConcertDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConcertMapper {
    List<ConcertDto> findAllConcertList();

    ConcertDto getConcertDto(@Param("concertId") Long concertId);

    List<ConcertDto> getNewConcertList();
}
