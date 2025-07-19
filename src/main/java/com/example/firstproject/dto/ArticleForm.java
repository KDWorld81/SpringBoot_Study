package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class ArticleForm {
    private Long id;
    // 아래 두개는 클라이언트로부터 전달받을 데이터
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드


    //DTO를 Entity로 바꿔주는 메서드 (DB저장 직전에 사용됨)
    public Article toEntity() {
        return new Article(id,title,content);
    }
}
