package com.ohgiraffers.sessionlogin.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberRoleDTO {

    private int memberNo;
    private int authorityCode;

    /* 권한 코드별로 가지는 권한을 나타냄 */
    private AuthorityDTO authority;
}
