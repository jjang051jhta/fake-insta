package com.jjang051.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Story extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String caption;

    private String imgUrl;

    @Transient  //참조용 변수 만들어 쓸때
    private int likeTotal;

    @Transient
    private boolean likeState;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Story(int id, String caption, String imgUrl, int likeTotal, boolean likeState, Member member) {
        this.id = id;
        this.caption = caption;
        this.imgUrl = imgUrl;
        this.likeTotal = likeTotal;
        this.likeState = likeState;
        this.member = member;
    }
}
