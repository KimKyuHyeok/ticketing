package com.ticket.ticketing.service;

import com.ticket.ticketing.dto.TipDto;
import com.ticket.ticketing.dto.response.ResponseTipDto;
import com.ticket.ticketing.mapper.TipMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TipService {

    private final TipMapper tipMapper;

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
}
