package com.ticket.ticketing.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseFreeBoardDto {
    private Long freeId;
    private String nickname;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private boolean deleted;
}
