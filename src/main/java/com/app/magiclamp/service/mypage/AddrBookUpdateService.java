package com.app.magiclamp.service.mypage;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.model.mypage.AddrBookRequest;
import com.app.magiclamp.repository.AddrBookRepository;
import com.app.magiclamp.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class AddrBookUpdateService {

    @Autowired
    private AddrBookRepository addrBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public AddrBook updateAddrBook(AddrBookRequest addrBookRequest){
        AddrBook addrBook = addrBookRequest.toAddrBookEntity();
        log.info("###################" + addrBook);
        if(addrBookRequest.getPriority() == 1){

            AddrBook chk = addrBookRepository.selectAddrBookByPriority(addrBookRequest.getUserindex(), addrBookRequest.getAddrindex());
            log.info(chk);

            if(chk != null && (chk.getAddrindex() != addrBookRequest.getAddrindex())){
                addrBookRepository.updateAddrBookUserInfoPriority(addrBookRequest.getUserindex());
            }

            userRepository.updateAddressByAddrBook(addrBookRequest.getPostnum(),
                    addrBookRequest.getAddress1(),
                    addrBookRequest.getAddress2(),
                    addrBookRequest.getPhone(),
                    addrBookRequest.getUserindex());

            log.info("유저 정보 업데이트");
        }
        else{
            AddrBook chk = addrBookRepository.selectAddrBookByPriority(addrBookRequest.getUserindex(), addrBookRequest.getAddrindex());
            log.info("###################" + chk.getAddrindex());
            if((Integer)chk.getAddrindex() == (Integer)addrBookRequest.getAddrindex()){
                return null;
            }
        }


        log.info("################## addrbook" + addrBook);
        return addrBookRepository.save(addrBook);
    }
}
