package com.ohgiraffers.sessionlogin.member.dao;

import com.ohgiraffers.sessionlogin.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberDTO findMemberById(String memberId);

    void registMember(MemberDTO member);

    void addMemberAuthority();
}
