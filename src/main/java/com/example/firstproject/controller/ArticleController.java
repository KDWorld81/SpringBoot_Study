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

import java.util.ArrayList;
import java.util.List;
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
        Article saved = articleRepository.save(article); //saved는 Article 엔티티 객체
        log.info(saved.toString()); // println문 대신 로깅코드
       // System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    // id를 통한 Read
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

    // List 전체조회
    @GetMapping("/articles")
    public String index(Model model){
        // 1. DB에서 모든 Article 엔티티 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll(); // List<article>을 사용하면 DB에서 조회한 데이터 묶음을 리스트에 담긴 Article 타입으로 가져옴

        // 2. 가져온 Article 묶음을 모델에 등록하기
        model.addAttribute("articleList", articleEntityList); // 전달할 데이터 묶음인 article EntityList를 articleList라는 이름으로 등록함

        // 3. 사용자에게 보여줄 뷰 페이지 설정하기
        return "articles/index";
    }

    // 수정
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 1. 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ // 매개변수로 DTO 받아오기
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환
        Article articleEntity = form.toEntity(); // DTO(form)를 엔티티(articleEntity)로 변환
        log.info(articleEntity.toString());
        // 2. 엔티티를 DB에 저장
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null); // DB에서 기존데이터 가져오기
        if(target != null){
            articleRepository.save(articleEntity); // 엔티티를 DB에 저장(갱신)
        }
        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id){
        log.info("삭제 요청이 들어왔습니다!!");

        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 대상 엔티티 삭제하기
        if(target != null){
            articleRepository.delete(target); // repository가 DB에서 target을 삭제
        }

        // 3. 결과 페이지로 리다이렉트
        return "redirect:/articles";

    }

}
