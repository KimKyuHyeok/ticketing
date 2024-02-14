package com.ticket.ticketing.mapper;

import com.ticket.ticketing.dto.response.ResponseTipCommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TipCommentMapper {

    void createComment(
            @Param("tipId") Long tipId,
            @Param("userId") Long userId,
            @Param("comment") String comment);

    List<ResponseTipCommentDto> findAllTipComment(@Param("tipId") Long tipId);
}
