package com.example.springboard.preference.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PreferenceVO {
    private int id;
    private int postId;
    private int memberId;
    private PreferenceType type;
    
    public PreferenceVO(int postId, int memberId) {
        this.postId = postId;
        this.memberId = memberId;
    }
    
    public enum PreferenceType {
        LIKE,
        DISLIKE
    }
}
