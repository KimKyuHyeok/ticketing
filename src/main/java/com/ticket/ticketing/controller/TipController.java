package com.ticket.ticketing.controller;


import com.ticket.ticketing.dto.response.ResponseTipDto;

import com.ticket.ticketing.service.TipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class TipController {

    private final TipService tipService;

    @GetMapping("/board")
    public String tipBoardPage(
            @RequestParam(required = false, defaultValue = "") String searchType,
            @RequestParam(required = false, defaultValue = "") String searchValue,
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) {
        List<ResponseTipDto> boardList = tipService.getBoardList(page, searchType, searchValue);
        int total = tipService.getBoardTotalNumber();

        model.addAttribute("page", page);
        model.addAttribute("totalPages", 20);
        model.addAttribute("boardList", boardList);

        return "board/tip";
    }

    @GetMapping("/board/form")
    public String formPage() {

        return "board/form";
    }

    @PostMapping("/board/form/write")
    public String formWrite(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpSession session,
            Model model
    ) {
        String userIdStr = session.getAttribute("num").toString();
        Long userId = Long.parseLong(userIdStr);

        try {
            tipService.upload(title, content, userId);

            return "redirect:/board";

        } catch (Exception e) {
            return "false";
        }

    }
}
