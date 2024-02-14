package com.ticket.ticketing.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseTipCommentDto {

    private Long commentId;
    private Long tipId;
    private String nickname;
    private String comment;
    private LocalDateTime createdAt;
    private boolean deleted;
}
