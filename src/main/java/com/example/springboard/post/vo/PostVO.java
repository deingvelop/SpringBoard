package com.example.springboard.post.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PostVO {
	private int id;
	private String title;
	private String content;
	private int memberId;
	private String memberNickname;
	private int likeCnt;
	private int dislikeCnt;
	private LocalDate postDate;
}
