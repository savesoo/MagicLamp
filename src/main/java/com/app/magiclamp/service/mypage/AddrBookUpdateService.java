package com.app.magiclamp.service.mypage;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.model.mypage.AddrBookRequest;
import com.app.magiclamp.repository.AddrBookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AddrBookUpdateService {

    @Autowired
    private AddrBookRepository addrBookRepository;

    public AddrBook updateAddrBook(AddrBookRequest addrBookRequest){
        AddrBook addrBook = addrBookRequest.toAddrBookEntity();

        return addrBookRepository.save(addrBook);
    }
}
