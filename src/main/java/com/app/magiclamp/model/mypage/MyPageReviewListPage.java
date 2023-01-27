package com.app.magiclamp.model.mypage;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MyPageReviewListPage {

    // 페이지당 출력할 게시물의 개수
    private int countPerPage;

    // 현재 페이지 번호
    private int pageNum;

    // 게시물의 리스트
    private List<MyPageReviewListDTO> list;

    // 전체 게시물의 개수
    private int totalCount;

    // 페이징의 시작 번호
    private int startNum;

    // 페이직의 끝 번호
    private int endNum;

    // 이전 번튼 출력 여부
    private boolean prev;

    // 다음 버튼 출력 여부
    private boolean next;

    private String title;

    private LocalDate sDate;

    private LocalDate eDate;

    public MyPageReviewListPage(
            int countPerPage,
            int pageNum,
            List<MyPageReviewListDTO> list,
            int totalCount,
            String title,
            LocalDate sDate,
            LocalDate eDate) {
        this.countPerPage = countPerPage;
        this.pageNum = pageNum;
        this.list = list;
        this.totalCount = totalCount;
        this.title = title;
        this.sDate = sDate;
        this.eDate = eDate;
        calPageInfo();
    }

    private void calPageInfo() {

        // 끝번호
        this.endNum = (int)(Math.ceil((this.pageNum * 1.0) / 10)) * 10;    // 11 -> 11.0 / 10 -> 1.1 -> 2.0 -> 2 * 10
        // 1-5     : 5
        // 6-10    : 10
        // 11-15   : 15
        // 시작번호
        this.startNum = this.endNum-9;

        // 전체 페이지의 끝번호
        int realEndNum =    (int)( Math.ceil((this.totalCount * 1.0) / countPerPage));

        // 구간의 막지막 번호보다 실제 페이지 끝번호가 작으면 endNum 은 실제 끝번호가 되어야 한다!!!
        this.endNum = realEndNum == 0 ? 1 : (realEndNum < this.endNum ? realEndNum : this.endNum);

        // 이전 구간으로 이동가능한지
        this.prev = this.startNum > 1;

        // 다음 구간 으로 갈수 있는지
        this.next = this.endNum < realEndNum;
    }

}
