package com.ticket.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long userId;
    private String loginId;
    private String password;
    private String email;
    private String nickname;
    private String role;
}
