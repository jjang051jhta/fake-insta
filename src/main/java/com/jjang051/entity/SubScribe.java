package com.jjang051.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
            @UniqueConstraint(
                    name="subscribe_uk",
                    columnNames = {"fromMemberId","toMemberId"}
            )
})
public class SubScribe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fromMemberId")
    private Member fromMember;

    @ManyToOne
    @JoinColumn(name = "toMemberId")
    private Member toMember;


    //1 --- 2
    //1 --- 3
}





