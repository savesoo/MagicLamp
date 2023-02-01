package com.app.magiclamp.service.mypage;

import com.app.magiclamp.mapper.CartMapper;
import com.app.magiclamp.model.mypage.CartListDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CartListService {

    @Autowired
    private CartMapper cartMapper;

    public List<CartListDTO> selectCartList(int userindex, int option){

        List<CartListDTO> list = cartMapper.selectCartList(userindex, option);

        log.info("################ cart service List >> " + list);

        return list;

    }

}
