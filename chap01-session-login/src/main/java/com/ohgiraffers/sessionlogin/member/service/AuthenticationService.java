package com.ohgiraffers.sessionlogin.member.service;

import com.ohgiraffers.sessionlogin.member.dao.MemberMapper;
import com.ohgiraffers.sessionlogin.member.dto.CustomUser;
import com.ohgiraffers.sessionlogin.member.dto.MemberDTO;
import com.ohgiraffers.sessionlogin.member.dto.MemberRoleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j //롬복이 가지고 있는 어노테이션,
@Service
public class AuthenticationService implements UserDetailsService {

    private final MemberMapper memberMapper;

    public AuthenticationService(MemberMapper memberMapper) { this.memberMapper = memberMapper; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /* 전달 된 아이디 확인 */
        log.info("username : {}", username);

        /* 사용자 정의 타입으로 유저 조회 */
        MemberDTO member = memberMapper.findMemberById(username);

        /* 조회 된 유저 확인 */
        log.info("member : {}", member);

        /* 일치하는 아이디가 없어서 조회 된 유저가 없을 경우 */
        if(member == null) throw new UsernameNotFoundException("username not founf");

        /* 권한 리스트 만들기 */
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(MemberRoleDTO role : member.getMemberRoleList()) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority().getName()));
        }

        /* UserDetails를 구현한 User 객체에 id, pwd, 권한을 전달해서 객체를 생성하고 반환한다. */
        // return new User(member.getId(), member.getPwd(), authorities);

        /* User 객체에는 담을 수 없는 추가 정보를 User를 상속한 CustomUser로 처리한다. */
        return  new CustomUser(member, authorities);
    }
}
