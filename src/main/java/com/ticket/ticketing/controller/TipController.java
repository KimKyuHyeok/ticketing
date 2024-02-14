package com.ticket.ticketing.controller;


import com.ticket.ticketing.dto.response.ResponseTipCommentDto;
import com.ticket.ticketing.dto.response.ResponseTipDto;

import com.ticket.ticketing.service.ImageService;
import com.ticket.ticketing.service.TipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class TipController {

    private final TipService tipService;

    private ImageService imageService;

    @Autowired
    public void ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

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

    @GetMapping("/board/{tipId}")
    public String tipBoardDetailPage(
            @PathVariable Long tipId,
            Model model
    ) {
        ResponseTipDto tipDto = tipService.findSelectOne(tipId);
        List<ResponseTipCommentDto> commentList = tipService.findAllTipComment(tipId);

        model.addAttribute("tip", tipDto);
        model.addAttribute("comments", commentList);

        return "board/tip-detail";
    }

    @GetMapping("/board/form")
    public String formPage() {

        return "board/form";
    }

    @PostMapping("/board/form/write")
    public String formWrite(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpServletRequest request,
            Model model
    ) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("num")) {
                    String idValue = cookie.getValue();
                    Long userId = Long.parseLong(idValue);

                    tipService.upload(title, content, userId);

                    return "redirect:/board";
                }
            }
        }
        return null;
    }


}
