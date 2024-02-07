package com.ticket.ticketing.controller;

import com.ticket.ticketing.dto.ConcertDto;
import com.ticket.ticketing.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final ConcertService concertService;

    @GetMapping
    public String home(Model model) {

        List<ConcertDto> concertDtoList = concertService.getNewConcertList();

        model.addAttribute("concertList", concertDtoList);

        return "home";
    }
}
