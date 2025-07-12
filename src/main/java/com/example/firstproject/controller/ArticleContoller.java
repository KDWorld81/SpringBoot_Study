package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleContoller {
    @Autowired // 스프링부트가 미리 생성해놓은 리파지터리 객체 주입
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ // 폼 데이터를 DTO로 받기
        log.info(form.toString()); // println문 대신 로깅코드
        //System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지?
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString()); // println문 대신 로깅코드
       // System.out.println(article.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString()); // println문 대신 로깅코드
       // System.out.println(saved.toString());
        return "";
    }

}
