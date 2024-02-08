package com.ticket.ticketing.mapper;

import com.ticket.ticketing.dto.request.ConcertUploadDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    void concertUpload(@Param("concert") ConcertUploadDto concertUploadDto, @Param("image") String imageUrl);
}
