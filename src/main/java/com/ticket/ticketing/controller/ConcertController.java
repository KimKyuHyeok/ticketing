package com.ticket.ticketing.controller;

import com.ticket.ticketing.dto.ConcertDto;
import com.ticket.ticketing.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping("/popup/{concertId}")
    public String popup(Model model, @PathVariable Long concertId) {
        ConcertDto concertDto = concertService.getConcertDto(concertId);

        model.addAttribute("concert", concertDto);

        return "popup";
    }
}
