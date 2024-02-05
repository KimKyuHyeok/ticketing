package com.ticket.ticketing.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoUpdateDto {
    private String password;
    private String email;
    private String nickname;
}
