package com.example.springboard.comment.vo;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO {
	private int id;
	private int postId;
	private int memberId;
	private String memberNickname;
	private String content;
	private LocalDate commentDate;
}
