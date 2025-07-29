package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired // 게시글 Repo 주입 (의존성 주입)
    private ArticleService articleService; // 서비스 객체 주입
    //GET
    // 전체 게시글 조회
    @GetMapping("/api/articles")
    public List<Article> index() { // Article을 반환하므로 반환형이 List<Article>
        return articleService.index();
    }
    // 단일 게시글 조회
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

//    //POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto){
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//
//    //PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
//        // 1. 수정용 엔티티 생성 ( DTO -> 엔티티 변환)
//        Article article = dto.toEntity();
//        log.info("id : {}, article : {}", id,article.toString());
//
//        // 2. DB에 해당 엔티티가 있는지 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 잘못된 요청 처리
//        if(target == null || id!= article.getId()){
//            log.info("잘못된 요청! id : {}, article : {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 4. 업데이트 및 정상응답
//        target.patch(article);
//        Article updated = articleRepository.save(article); // article 엔티티에 담긴 수정용 데이터를 DB에 저장 후 updated라는 변수에 저장
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//
//    }
//
//    //DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//        // 1. DB에서 해당 엔티티 있는지 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 2. 대상 엔티티 없어 잘못된 요청 처리
//        if(target == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 3. 대상 삭제 정상응답 반환
//        articleRepository.delete((target));
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
