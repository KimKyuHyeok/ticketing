package com.ticket.ticketing.mapper;

import com.ticket.ticketing.dto.response.ResponseFreeBoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FreeBoardMapper {

    List<ResponseFreeBoardDto> findAll(@Param("pageNumber") int pageNumber);

    List<ResponseFreeBoardDto> findAllByTitle(
            @Param("searchValue") String searchValue,
            @Param("pageNumber") int pageNumber
    );

    List<ResponseFreeBoardDto> findAllByNickname(
            @Param("searchValue") String searchValue,
            @Param("pageNumber") int pageNumber
    );
}
