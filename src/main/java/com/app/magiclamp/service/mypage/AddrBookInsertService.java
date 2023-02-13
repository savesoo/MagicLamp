package com.app.magiclamp.service.mypage;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.model.mypage.AddrBookRequest;
import com.app.magiclamp.repository.AddrBookRepository;
import com.app.magiclamp.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AddrBookInsertService {

    @Autowired
    private AddrBookRepository addrBookRepository;
    @Autowired
    private UserRepository userRepository;

    public int insertAddrBook(AddrBookRequest addrBookRequest){
        AddrBook addrBook = addrBookRequest.toAddrBookEntity();

        if(addrBookRequest.getPriority() == 1){
            AddrBook chk = addrBookRepository.selectAddrBookByPriority(addrBookRequest.getUserindex(), addrBookRequest.getAddrindex());

            if(chk != null){
                addrBookRepository.updateAddrBookUserInfoPriority(addrBookRequest.getUserindex());
            }

            userRepository.updateAddressByAddrBook(addrBookRequest.getPostnum(),
                    addrBookRequest.getAddress1(),
                    addrBookRequest.getAddress2(),
                    addrBookRequest.getPhone(),
                    addrBookRequest.getUserindex());
        }

        return addrBookRepository.save(addrBook) != null ? 1 : 0;
    }
}
