package com.example.springboard.comment.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springboard.comment.service.CommentService;
import com.example.springboard.comment.vo.CommentVO;
import com.example.springboard.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/post/{postId}")
public class CommentController {
    private final CommentService commentService;
    
    @ModelAttribute("comment")
    public CommentVO createCommentVO() {
        return new CommentVO();
    }
    
    @PostMapping("comment")
    public String createComment(@PathVariable("postId") int postId, @ModelAttribute("comment") CommentVO requestVO, HttpSession session) {
        
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

        requestVO.setPostId(postId);
        requestVO.setMemberId(loginMember.getId());
        requestVO.setMemberNickname(loginMember.getNickname());
        
        log.info("VO=" + requestVO);
        
        commentService.createComment(requestVO);
        
        return "redirect:/post/" + postId;
    }
    
    @PostMapping(value = "comment/{commentId}")
    public String updateComment(@PathVariable("postId") int postId, @PathVariable("commentId") int commentId, @ModelAttribute("comment") CommentVO requestVO, HttpSession session) {
        log.info("수정하려고 들어온 comment 데이터=" + requestVO);
        
        requestVO.setId(commentId);
        commentService.updateComment(requestVO);
        
        log.info("comment 수정 controller 메서드 수행 완료");
        
        return "redirect:/post/" + postId;
    }
    
    @DeleteMapping(value = "comment/{commentId}")
    public String deleteComment(@PathVariable("postId") int postId, @PathVariable("commentId") int commentId, HttpSession session) {
        
        commentService.deleteComment(commentId);
                
        return "redirect:/springboard/post/" + postId;
        }
}
