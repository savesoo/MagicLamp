package com.app.magiclamp.service.mypage;

import com.app.magiclamp.repository.CartRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class CartDeleteService {

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public int deleteCart(List<Integer> cartindex, int userindex){

    int result = 0;

        for(int idx : cartindex){
            result = cartRepository.deleteByCartindex(idx, userindex);
            log.info("########################################### delete idx : " + idx +"result : "+ result);
        }

        return result;
    }
}
