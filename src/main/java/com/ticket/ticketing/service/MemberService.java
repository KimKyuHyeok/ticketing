package com.ticket.ticketing.service;

import com.ticket.ticketing.dto.MemberDto;
import com.ticket.ticketing.dto.request.LoginRequest;
import com.ticket.ticketing.dto.request.MemberInfoUpdateDto;
import com.ticket.ticketing.dto.request.MemberJoinDto;
import com.ticket.ticketing.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberDto login(String loginId, String password) {

        MemberDto memberInfo = memberMapper.login(loginId);

        String hashPassword = memberInfo.getPassword();

        boolean isMatch = BCrypt.checkpw(password, hashPassword);

        if (isMatch == true) {
            return memberInfo;
        } else {
            return null;
        }
    }

    public MemberDto findByMemberId(Long userId) {
        return memberMapper.findByMemberId(userId);
    }

    public void memberInfoUpdate(MemberInfoUpdateDto memberInfoUpdateDto, Long userId) {

        try {
            String password = memberInfoUpdateDto.getPassword();
            String email = memberInfoUpdateDto.getEmail();
            String nickname = memberInfoUpdateDto.getNickname();
            String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            memberMapper.memberInfoUpdate(hashPassword, email, nickname, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkNickname(String nickname, Long userId) {
        boolean result = memberMapper.checkNickname(nickname, userId).isPresent();

        return result;
    }

    public boolean checkEmail(String email, Long userId) {
        boolean result = memberMapper.checkEmail(email, userId).isPresent();

        return result;
    }

    public boolean join(MemberJoinDto memberJoinDto) {

        try {
            String loginId = memberJoinDto.getLoginId();
            String password = memberJoinDto.getPassword();
            String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            String email = memberJoinDto.getEmail();
            String nickname = memberJoinDto.getNickname();

            memberMapper.join(loginId, hashPassword, email, nickname);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean joinCheckLoginId(String loginId) {
        System.out.println(">>>>>>" + memberMapper.joinCheckLoginId(loginId).toString());

        boolean result = memberMapper.joinCheckLoginId(loginId).isPresent();

        return result;
    }

    public boolean joinCheckEmail(String email) {
        boolean result = memberMapper.joinCheckEmail(email).isPresent();

        return result;
    }

    public boolean joinCheckNickname(String nickname) {
        boolean result = memberMapper.joinCheckNickname(nickname).isPresent();

        return result;
    }
}
