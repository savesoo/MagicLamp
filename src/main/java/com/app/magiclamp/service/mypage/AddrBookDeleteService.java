package com.app.magiclamp.service.mypage;

import com.app.magiclamp.repository.AddrBookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class AddrBookDeleteService {

    @Autowired
    private AddrBookRepository addrBookRepository;

    @Transactional
    public int deleteAddrBook(List<Integer> addrindex, int userindex){

    int result = 0;

        for(int idx : addrindex){
            result = addrBookRepository.deleteByAddrindex(idx, userindex);
            log.info("########################################### delete idx : " + idx +"result : "+ result);
        }

        return result;
    }
}
