package com.app.magiclamp.mapper;

import com.app.magiclamp.model.review.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper {

    // 평점
    @Select("select round(avg(star), 1) from tbl_review group by isbn having isbn=#{isbn}")
    Double calculateTheAverageOfStar(String isbn);

    // 리뷰 목록
    /*@Select("select * from tbl_review as r left outer join tbl_user as u on r.reviewer=u.userindex where r.isbn=#{isbn} order by r.reviewindex desc limit #{start}, #{end}")*/
    @Select("select c.cnt, u.username, r.*from tbl_review as r left outer join (select * from tbl_user) as u on r.reviewer=u.userindex left outer join (select reviewindex, count(reviewindex) as cnt from tbl_like group by reviewindex) as c on r.reviewindex=c.reviewindex where r.isbn=#{isbn} order by r.reviewindex desc limit #{start}, #{end}")
    List<ReviewDTO> getList(@Param("isbn") String isbn, @Param("start") int start, @Param("end") int end);

    @Select("select c.cnt, u.username, r.*from tbl_review as r left outer join (select * from tbl_user) as u on r.reviewer=u.userindex left outer join (select reviewindex, count(reviewindex) as cnt from tbl_like group by reviewindex) as c on r.reviewindex=c.reviewindex where r.isbn=#{isbn} order by c.cnt desc limit #{start}, #{end}")
    List<ReviewDTO> findAllByLike(@Param("isbn") String isbn, @Param("start") int start, @Param("end") int end);

    @Select("select c.cnt, u.username, r.*from tbl_review as r left outer join (select * from tbl_user) as u on r.reviewer=u.userindex left outer join (select reviewindex, count(reviewindex) as cnt from tbl_like group by reviewindex) as c on r.reviewindex=c.reviewindex where r.isbn=#{isbn} order by r.star desc limit #{start}, #{end}")
    List<ReviewDTO> findAllByHighRating(@Param("isbn") String isbn, @Param("start") int start, @Param("end") int end);
    
    @Select("select count(*) from tbl_orderitem i inner join tbl_order o on i.orderindex=o.orderindex where i.isbn=#{isbn} and o.userindex=#{userindex}")
    Long countByUserindexAndIsbn(@Param("isbn") String isbn, @Param("userindex") int userindex);
}
