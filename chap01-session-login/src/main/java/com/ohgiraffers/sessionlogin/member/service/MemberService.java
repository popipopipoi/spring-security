package com.ohgiraffers.sessionlogin.member.service;

import com.ohgiraffers.sessionlogin.member.dao.MemberMapper;
import com.ohgiraffers.sessionlogin.member.dto.MemberDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional   // Exception 발생하면 롤백 아니면 정상 처리 해주는 어노테이션
    public void joinMember(MemberDTO member) {
        /* 평문으로 된 비밀번호를 암호화한다. */
        member.setPwd(passwordEncoder.encode(member.getPwd()));
        /* tbl_member 테이블에 정보 저장 */
        memberMapper.registMember(member);
        /* tbl_member_role 테이블에 권한 정보 저장 */
        memberMapper.addMemberAuthority();
    }
}
