package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    // 특정 게시글의 모든댓글 조회 - 쿼리 어노테이션 사용
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId",
    nativeQuery = true) // value 속성에 실행하려는 쿼리
    List<Comment> findByArticleId(Long articleid);

    // 특정 닉네임의 모든댓글 조회 - orm.xml 파일 이용
    List<Comment> findByNickname(String nickname);


}
