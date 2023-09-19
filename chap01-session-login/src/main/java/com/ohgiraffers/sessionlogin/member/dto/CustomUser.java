package com.ohgiraffers.sessionlogin.member.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

@Getter
@ToString
public class CustomUser extends User {

    private int no;
    private String name;
    private Date registDate;
    public CustomUser(MemberDTO member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getId(), member.getPwd(), authorities);
        setDetails(member);
    }

    private void setDetails(MemberDTO member) {
        this.no = member.getNo();
        this.name = member.getName();
        this.registDate = member.getRegistDatetime();
    }
}
