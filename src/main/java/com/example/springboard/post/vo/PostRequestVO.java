package com.example.springboard.post.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequestVO {
	private String title;
	private String content;
	private int memberId;
	private String memberNickname;
	public void setMemberId(int id) {
		// TODO Auto-generated method stub
		
	}
}
