package com.app.magiclamp.service.cart.order;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.entity.Mileage;
import com.app.magiclamp.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderPageViewService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AddrBookRepository addrBookRepository;

    @Autowired
    private MileageRepository mileageRepository;




    public AddrBook getUserAddress(int userindex){

        // 저장된 배송정보
        // userindex, priority=1로 AddrBook select

        return addrBookRepository.findByUserindexAndPriority(userindex, 1);

    }


    public Mileage getCurrentMileage(int userindex){

        // 현재 마일리지
        // userindex로 마일리지 select

        return mileageRepository.findById(userindex).get();

    }


}


