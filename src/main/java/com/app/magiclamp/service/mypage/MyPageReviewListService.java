package com.app.magiclamp.service.mypage;

import com.app.magiclamp.mapper.MyPageReviewMapper;
import com.app.magiclamp.model.mypage.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class MyPageReviewListService {

    @Autowired
    private MyPageReviewMapper mypageMapper;

    public MyPageReviewListPage getMyPageReviewList(int userindex, int pageNum, MyReviewSearchOption option){

            int page = (pageNum -1) * 10;

            log.info("############## service option >> " + option);

            List<MyPageReviewListDTO> list = mypageMapper.selectMyPageReviewList(userindex, page, option);

            log.info("################ service List >> " + list);

            int totalCount = mypageMapper.selectMyPageReviewListTotalCount(userindex, option);

            MyPageReviewListPage myPageReviewListPage = new MyPageReviewListPage(10, pageNum, list, totalCount, option.getSearchTitle(), option.getSDate(), option.getEDate());

        return myPageReviewListPage;

    }

}
