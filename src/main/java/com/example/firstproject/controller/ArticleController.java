package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {
    private final ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ // 폼 데이터를 DTO로 받기
        log.info(form.toString()); // println문 대신 로깅코드
        //System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지?
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity(); // 폼데이터를 DB에 넣기위해 Entity로 변환
        log.info(article.toString()); // println문 대신 로깅코드
       // System.out.println(article.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString()); // println문 대신 로깅코드
       // System.out.println(saved.toString());
        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){ // 매개변수로 id 받아오기
        log.info("id = "+id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //   2. 모델에 데이터 등록
        model.addAttribute("article", articleEntity); // article이라는 이름으로 articleEntity 등록
        //   3. 뷰페이지 만들어 반환
        return "articles/show";

    }

}
