package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

@Entity // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 DB에 테이블 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개 변수가 아예 없는 기본 생성자 자동 생성
public class Comment {

    @Id // 대표키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long id; // 대표키

    @ManyToOne // Comment 엔티티와 Article 엔티티를 다대일 관계로 설정
    @JoinColumn(name="article_id") // 외래키 생성, Article 엔티티의 기본 키(id)와 매핑
    private Article article; // 해당 댓글의 부모 게시글

    @Column  // 해당 필드를 테이블의 속성으로 매핑
    private String nickname; // 댓글을 단 사람

    @Column // 해당 필드를 테이블의 속성으로 매핑
    private String body; // 댓글 본문

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        if (dto.getId() != null){  // dto에 id가 존재하는 경우
            throw new IllegalStateException("댓글 생성 실패! 댓글의 id가 없어야 합니다!");
        }
        if (dto.getArticleId() != article.getId()){ // dto에서 가져온 부모 게시글과 엔티티에서 가져온 부모 게시글의 id가 다를 경우
            throw new IllegalStateException("댓글 생성 실패! 게시글의 id가 잘못됐습니다!");
        }

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),  // 댓글 id
                article,  // 부모 게시글
                dto.getNickname(), // 댓글 닉네임
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId()){
            throw new IllegalStateException("댓글 수정 실패! 잘못된 Id가 입력되었습니다.");
        }
        // 객체 갱신
        if (dto.getNickname() != null){  // 수정할 닉네임 데이터가 있다면
            this.nickname = dto.getNickname();  // 내용 변경
        }
        if (dto.getBody() != null){  // 수정할 본문 데이터가 있다면
            this.body = dto.getBody();  // 내용 반영
        }
    }
}