package com.app.magiclamp.service.mypage;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.repository.AddrBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddrBookListService {

    @Autowired
    private AddrBookRepository addrBookRepository;

    public List<AddrBook> selectAddrBookAll(int userindex){

        return addrBookRepository.findByUserindex(userindex);

    }
}
