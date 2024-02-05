package com.ticket.ticketing.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinDto {
    private String loginId;
    private String password;
    private String email;
    private String nickname;
}
