package com.example.springboard.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequestVO {
	private String username;
	private String password;
}
