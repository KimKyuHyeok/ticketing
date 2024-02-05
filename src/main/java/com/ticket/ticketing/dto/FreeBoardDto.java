package com.ticket.ticketing.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FreeBoardDto {
    private Long freeId;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private boolean deleted;
}
