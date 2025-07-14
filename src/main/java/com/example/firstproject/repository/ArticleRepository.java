package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// 이걸 함으로써 ArticleRepository는 CRUD를 사용가능
// 엔티티가 DB 속 테이블에 저장 및 관리될수 있게 함
public interface ArticleRepository extends CrudRepository<Article,Long> { // Article : 관리대상 엔티티의 클래스타입, Long 관리대상 엔티티의 대푯값 타입
    @Override
    ArrayList<Article> findAll(); // Iterable -> ArrayList 수정때문에 오버라이딩 해줌.
}
