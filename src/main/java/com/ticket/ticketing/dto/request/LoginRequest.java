package com.ticket.ticketing.dto.request;

import lombok.*;

@Data
public class LoginRequest {
    private String loginId;
    private String password;
}
