package com.jjang051.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @JsonIgnoreProperties({"stories"})  //json만들고 싶지 않을때...
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
