package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {
    @Autowired // 게시글 Repo 주입 (의존성 주입)
    private ArticleRepository articleRepository;
    //GET
    // 전체 게시글 조회
    @GetMapping("/api/articles")
    public List<Article> index() { // Article을 반환하므로 반환형이 List<Article>
        return articleRepository.findAll();
    }
    // 단일 게시글 조회
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //PATCH

    //DELETE
}
