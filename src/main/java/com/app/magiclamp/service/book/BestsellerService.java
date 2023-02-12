package com.app.magiclamp.service.book;

import com.app.magiclamp.mapper.BookMapper;
import com.app.magiclamp.model.book.BestsellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestsellerService {

    @Autowired
    private BookMapper bookMapper;

    public List<BestsellerDTO> getBestTen(){

        List<BestsellerDTO> list = bookMapper.selectBookBestTen();

        return list;

    }
}
