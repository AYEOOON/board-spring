package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 추가 어노테이션
@ToString
@Entity // 엔티티 선언
@Getter
public class Article {
    @Id // 엔티티의 대표값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long Id;
    @Column // tile 필도 선언, DB 테이블의 tile 열과 연결됨
    private String title;
    @Column // content 필드 선언, DB 테이블의 content 열과 연결됨
    private String Content;

    public void patch(Article article) {
        if(article.title != null){
            this.title = article.title;
        }
        if (article.Content != null){
            this.Content = article.Content;
        }
    }
}
