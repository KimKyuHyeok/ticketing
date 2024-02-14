package com.ticket.ticketing.controller;

import com.ticket.ticketing.service.TipCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class TipCommentController {

    private final TipCommentService tipCommentService;

    @PostMapping("/board/{tipId}/comment")
    public String commentAdd(
            @PathVariable Long tipId,
            @RequestParam String content,
            HttpServletRequest request
            ) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("num")) {
                    String userIdStr = cookie.getValue();
                    Long userId = Long.parseLong(userIdStr);

                    tipCommentService.createComment(tipId, userId, content);
                }
            }
        }
        String url = "redirect:/board/" + tipId;
        return url;
    }
}
