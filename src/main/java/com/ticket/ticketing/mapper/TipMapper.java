package com.ticket.ticketing.mapper;

import com.ticket.ticketing.dto.TipDto;
import com.ticket.ticketing.dto.response.ResponseTipDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TipMapper {

    List<ResponseTipDto> findAll(@Param("pageNumber") int pageNumber);

    List<ResponseTipDto> findAllByTitle(
            @Param("searchValue") String searchValue,
            @Param("pageNumber") int pageNumber
    );

    List<ResponseTipDto> findAllByNickname(
            @Param("searchValue") String searchValue,
            @Param("pageNumber") int pageNumber
    );


    int findCnt();

    void upload(@Param("tipDto") TipDto tipDto);

    ResponseTipDto findSelectOne(@Param("tipId") Long tipId);

    Long getUserId(@Param("tipId") Long tipId);

    void update(
            @Param("tipId") Long tipId,
            @Param("title") String title,
            @Param("content") String content
    );

    void delete(@Param("tipId") Long tipId);
}
