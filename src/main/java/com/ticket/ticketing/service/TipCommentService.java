package com.ticket.ticketing.service;

import com.ticket.ticketing.mapper.TipCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TipCommentService {

    private final TipCommentMapper tipCommentMapper;


    public void createComment(Long tipId, Long userId, String content) {

        tipCommentMapper.createComment(tipId, userId, content);
    }
}
