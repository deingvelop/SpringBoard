package com.example.springboard.member.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberRequestVO {
	private String username;
	private String password;
	private String nickname;
}
