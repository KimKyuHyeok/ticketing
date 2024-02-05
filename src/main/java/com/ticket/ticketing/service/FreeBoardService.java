package com.ticket.ticketing.service;

import com.ticket.ticketing.dto.FreeBoardDto;
import com.ticket.ticketing.dto.response.ResponseFreeBoardDto;
import com.ticket.ticketing.mapper.FreeBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FreeBoardService {

    private final FreeBoardMapper freeBoardMapper;

    public List<ResponseFreeBoardDto> getBoardList(int page, String searchType, String searchValue) {
        int number = (page - 1) * 20;

        if (searchType.equals("제목")) {
            return freeBoardMapper.findAllByTitle(searchValue, number);
        } else if (searchType.equals("닉네임")) {
            return freeBoardMapper.findAllByNickname(searchValue, number);
        } else if (searchType == null) {
            return freeBoardMapper.findAll(number);
        }

        return freeBoardMapper.findAll(number);
    }
}
