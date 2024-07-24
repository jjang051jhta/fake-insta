package com.jjang051.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 300)
    private String content;
    //멤버와의 연관관계 설정

    @ManyToOne
    @JoinColumn(name="writerId")
    private Member writer;
}
