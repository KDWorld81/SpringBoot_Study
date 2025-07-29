package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return  articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null)
            return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. 수정용 엔티티 생성 ( DTO -> 엔티티 변환)
        Article article = dto.toEntity();
        log.info("id : {}, article : {}", id,article.toString());

        // 2. DB에 해당 엔티티가 있는지 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리
        if(target == null || id!= article.getId()){
            log.info("잘못된 요청! id : {}, article : {}", id, article.toString());
            return null;
        }

        // 4. 업데이트 및 정상응답
        target.patch(article);
        Article updated = articleRepository.save(article); // article 엔티티에 담긴 수정용 데이터를 DB에 저장 후 updated라는 변수에 저장
        return updated;
    }

    public Article delete(Long id) {
        // 1. 대상찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리하기
        if(target == null)
            return null;

        // 3. 대상삭제
        articleRepository.delete(target);
        return target; // DB에서 삭제한 대상을 컨트롤러에 반환
    }
}
