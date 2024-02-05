package com.ticket.ticketing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TipDto {
    private Long tipId;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private boolean deleted;
}
