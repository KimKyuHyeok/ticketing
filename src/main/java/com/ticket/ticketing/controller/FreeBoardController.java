package com.ticket.ticketing.controller;

import com.ticket.ticketing.dto.FreeBoardDto;
import com.ticket.ticketing.dto.response.ResponseFreeBoardDto;
import com.ticket.ticketing.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @GetMapping("/free-board")
    public String freeBoard(
            @RequestParam(required = false, defaultValue = "") String searchType,
            @RequestParam(required = false, defaultValue = "") String searchValue,
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) {
        List<ResponseFreeBoardDto> boardList = freeBoardService.getBoardList(page, searchType, searchValue);

        model.addAttribute("page", page);
        model.addAttribute("totalPages", 20);
        model.addAttribute("boardList", boardList);

        return "board/freeBoard";
    }
}
