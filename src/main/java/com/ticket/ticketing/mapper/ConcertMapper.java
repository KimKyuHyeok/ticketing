package com.ticket.ticketing.mapper;

import com.ticket.ticketing.dto.ConcertDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConcertMapper {
    List<ConcertDto> findAllConcertList();
}
