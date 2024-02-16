package com.ticket.ticketing.service;

import com.ticket.ticketing.dto.TipDto;
import com.ticket.ticketing.dto.response.ResponseTipCommentDto;
import com.ticket.ticketing.dto.response.ResponseTipDto;
import com.ticket.ticketing.mapper.TipCommentMapper;
import com.ticket.ticketing.mapper.TipMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TipService {

    private final TipMapper tipMapper;
    private final TipCommentMapper tipCommentMapper;

    public List<ResponseTipDto> getBoardList(int page, String searchType, String searchValue) {
        int number = (page - 1) * 20;



        if (searchType.equals("제목")) {
            return tipMapper.findAllByTitle(searchValue, number);
        } else if (searchType.equals("닉네임")) {
            return tipMapper.findAllByNickname(searchValue, number);
        } else if (searchType == null) {
            return tipMapper.findAll(number);
        }


        return tipMapper.findAll(number);
    }

    public int getBoardTotalNumber() {
        int totalCnt = tipMapper.findCnt();

        int totalPage = totalCnt / 20 + 1;

        return totalPage;
    }

    public void upload(String title, String content, Long userId) {
        try {
            TipDto tipDto = TipDto.builder()
                    .title(title)
                    .content(content)
                    .userId(userId)
                    .build();

            tipMapper.upload(tipDto);

        } catch (Exception e) {

        }

    }

    public ResponseTipDto findSelectOne(Long tipId) {
        return tipMapper.findSelectOne(tipId);
    }

    public List<ResponseTipCommentDto> findAllTipComment(Long tipId) {
        List<ResponseTipCommentDto> commentDtoList = tipCommentMapper.findAllTipComment(tipId);

        return commentDtoList;
    }

    public Long getUserId(Long tipId) {
        return tipMapper.getUserId(tipId);
    }

    public boolean update(Long tipId, String title, String content) {
        try {
            tipMapper.update(tipId, title, content);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public void delete(Long tipId, Long userId) {
        if (tipMapper.getUserId(tipId).equals(userId)) {
            tipMapper.delete(tipId);
        }
    }
}
