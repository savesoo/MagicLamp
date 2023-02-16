package com.app.magiclamp.service.administrator;

import com.app.magiclamp.mapper.AdminBookManageMapper;
import com.app.magiclamp.mapper.CartMapper;
import com.app.magiclamp.model.book.BookRequest;
import com.app.magiclamp.model.mypage.CartListDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class AdminBookManageService {

    @Autowired
    private AdminBookManageMapper adminBookManageMapper;

    public List<BookRequest> selectBookManageList(int stock, String isbn){

        List<BookRequest> list = adminBookManageMapper.selectBookManageList(stock, isbn);

        return list;

    }

    public int updateBookStock(int stock, String isbn){

        return adminBookManageMapper.updateBookStock(stock, isbn);
    }

    public int updateBookMilerate(int mileagerate, String isbn){

        return adminBookManageMapper.updateBookMilerate(mileagerate, isbn);
    }

}
