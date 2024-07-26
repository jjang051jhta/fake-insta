package com.jjang051.repository;

import com.jjang051.entity.SubScribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscribeRepository extends JpaRepository<SubScribe, Integer> {

    //select 를 제외한 나머지는 @Modifying를 넣어야지만 동작함...
    @Modifying
    @Query(value = "insert INTO SUBSCRIBE " +
            "(id,fromMemberId, toMemberId,regDate, modifyDate) VALUES " +
            "(SUBSCRIBE_SEQ.nextval,:fromMemberId, :toMemberId, sysdate, sysdate)"
            , nativeQuery = true
    )
    void subscribe(@Param("fromMemberId") int fromMemeberId,
                   @Param("toMemberId") int toMemeberId
                   );
}
