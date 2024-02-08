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

import javax.servlet.http.Cookie;
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
            HttpServletResponse response,
            Model model
    ) {

        MemberDto member = memberService.login(loginRequest.getLoginId(), loginRequest.getPassword());

        if (member == null) {
            model.addAttribute("loginFailed", true);
            return "member/login";
        }

        // 세션 대신 쿠키를 사용하여 세션 식별자를 저장
        Cookie idCookie = new Cookie("id", member.getLoginId());
        Cookie numCookie = new Cookie("num", String.valueOf(member.getUserId()));

        // 쿠키 유효 시간 설정 (초 단위)
        idCookie.setMaxAge(1800);
        numCookie.setMaxAge(1800);

        // 쿠키를 HTTP 응답 헤더에 추가
        response.addCookie(idCookie);
        response.addCookie(numCookie);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        // 세션을 무효화합니다.
        request.getSession().invalidate();

        // 클라이언트의 쿠키를 삭제합니다.
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 쿠키의 유효 시간을 0으로 설정하여 삭제합니다.
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPage(HttpServletRequest request, Model model) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("num")) {
                    String idValue = cookie.getValue();

                    Long userId = Long.parseLong(idValue);

                    MemberDto memberDto = memberService.findByMemberId(userId);

                    model.addAttribute("userInfo", memberDto);

                    return "member/myPage";
                }
            }
        }
        return null;
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
    public ResponseEntity<Boolean> joinCheckDuplicate(
            @RequestParam(required = false) String loginId,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String nickname
    ) {
        if (loginId != null) {
            boolean result = memberService.joinCheckLoginId(loginId);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (email != null) {
            boolean result =  memberService.joinCheckEmail(email);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (nickname != null) {
            boolean result =  memberService.joinCheckNickname(nickname);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return null;
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
