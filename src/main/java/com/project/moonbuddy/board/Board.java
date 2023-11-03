package com.project.moonbuddy.board;

import com.project.moonbuddy.domain.BaseTimeEntity;
import com.project.moonbuddy.user.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity(name = "board")
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "writer", nullable = false)
    private String writer;
    @Column(name = "content")
    private String content;
    @Column(name = "picture")
    private String picture;
    @Column(name = "likes")
    private int likes;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Reply> replyList;

    public void deletelike(){
        this.likes-=1;
    }
    public void addlike() {
        this.likes += 1;
    }
}
