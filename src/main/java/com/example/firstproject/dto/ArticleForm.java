package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleForm {
    // 아래 두개는 클라이언트로부터 전달받을 데이터
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 데이터를 잘 받았는지 확인할 toString() 메서드 추가
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    //DTO를 Entity로 바꿔주는 메서드 (DB저장 직전에 사용됨)
    public Article toEntity() {
        return new Article(null,title,content);
    }
}
