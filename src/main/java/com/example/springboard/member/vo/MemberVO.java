package com.example.springboard.member.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	private int id;
	private String username;
	private String password;
	private String nickname;
}
