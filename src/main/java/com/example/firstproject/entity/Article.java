package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
public class Article {
    @Id     // 엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동 생성 기능 추가 (숫자가 자동으로 매겨짐)
    private Long id;
    @Column     // title 필드 선언, DB 테이블의 title 열과 연결됨.
    private String title;
    @Column     // content 필드 선언, DB테이블의 content 열과 연결됨.
    private String content;

    // 수정하고 싶은것들만 수정가능하도록
    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }

    /*public Long getId() {
        return id;       @Getter 어노테이션 덕에 메서드 필요X
    }*/
}
