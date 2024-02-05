package com.ticket.ticketing.mapper;

import com.ticket.ticketing.dto.MemberDto;
import com.ticket.ticketing.dto.request.LoginRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;


@Mapper
public interface MemberMapper {

    MemberDto login(@Param("loginId") String loginId);

    MemberDto findByMemberId(@Param("userId") Long userId);

    void memberInfoUpdate(@Param("password") String password,
                          @Param("email") String email,
                          @Param("nickname") String nickname,
                          @Param("userId") Long userId);

    Optional<MemberDto> checkNickname(
            @Param("nickname") String nickname,
            @Param("userId") Long userId
    );

    Optional<MemberDto> checkEmail(
            @Param("email") String email,
            @Param("userId") Long userId
    );

    void join(
            @Param("loginId") String loginId,
            @Param("password") String hashPassword,
            @Param("email") String email,
            @Param("nickname") String nickname);

    Optional<MemberDto> joinCheckLoginId(String loginId);

    Optional<MemberDto> joinCheckEmail(String email);

    Optional<MemberDto> joinCheckNickname(String nickname);
}
