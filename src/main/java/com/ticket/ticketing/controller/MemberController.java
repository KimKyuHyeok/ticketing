package com.ticket.ticketing.controller;

import com.ticket.ticketing.dto.MemberDto;
import com.ticket.ticketing.dto.request.LoginRequest;
import com.ticket.ticketing.dto.request.MemberInfoUpdateDto;
import com.ticket.ticketing.dto.request.MemberJoinDto;
import com.ticket.ticketing.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute LoginRequest loginRequest,
            HttpServletRequest request,
            Model model
    ) {

        MemberDto member = memberService.login(loginRequest.getLoginId(), loginRequest.getPassword());

        if (member == null) {
            model.addAttribute("loginFailed", true);
            return "member/login";
        }

        request.getSession().invalidate();
        HttpSession session = request.getSession(true);

        session.setAttribute("id", member.getLoginId());
        session.setAttribute("num", member.getUserId());
        session.setMaxInactiveInterval(1800);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model model) {

        String userIdStr = session.getAttribute("num").toString();
        Long userId = Long.parseLong(userIdStr);

        MemberDto memberDto = memberService.findByMemberId(userId);

        model.addAttribute("userInfo", memberDto);

        return "member/myPage";
    }

    @PostMapping("/myPage/update")
    public String myPageUpdate(
            @RequestBody MemberInfoUpdateDto memberInfoUpdateDto,
            HttpSession session
    ) {
        String userIdStr = session.getAttribute("num").toString();
        Long userId = Long.parseLong(userIdStr);

        memberService.memberInfoUpdate(memberInfoUpdateDto, userId);

        return null;
    }

    @GetMapping("/member/check-duplicate")
    @ResponseBody
    public boolean checkDuplicate(
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String email,
            HttpSession session
    ) {
        String userIdStr = session.getAttribute("num").toString();
        Long userId = Long.parseLong(userIdStr);
        if (nickname != null) {
            return memberService.checkNickname(nickname, userId);

        } else if (email != null) {
            return memberService.checkEmail(email, userId);
        }

        return true;
    }

    @GetMapping("/member/join/check-duplicate")
    @ResponseBody
    public boolean joinCheckDuplicate(
            @RequestParam(required = false) String loginId,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String nickname
    ) {
        if (loginId != null) {
            return memberService.joinCheckLoginId(loginId);
        } else if (email != null) {
            return memberService.joinCheckEmail(email);
        } else if (nickname != null) {
            return memberService.joinCheckNickname(nickname);
        }

        return true;
    }

    @GetMapping("/join")
    public String joinPage() {
        return "member/join";
    }

    @PostMapping("/join/request")
    public ResponseEntity<Boolean> joinForm(@RequestBody MemberJoinDto memberJoinDto) {

        try {
            memberService.join(memberJoinDto);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

}
