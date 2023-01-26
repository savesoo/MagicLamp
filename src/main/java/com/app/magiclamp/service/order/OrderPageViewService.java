package com.app.magiclamp.service.order;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.entity.Mileage;
import com.app.magiclamp.entity.Book;
import com.app.magiclamp.model.BookInfoDTO;
import com.app.magiclamp.model.order.RequestOrderBook;
import com.app.magiclamp.model.order.OrderBookPageDTO;
import com.app.magiclamp.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class OrderPageViewService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AddrBookRepository addrBookRepository;

    @Autowired
    private MileageRepository mileageRepository;

    // 바로구매 클릭시 한 권 정보 get
    @Transactional
    public OrderBookPageDTO getOrderBook(RequestOrderBook orders, int userindex){

        log.info(" order view service ... ");

        OrderBookPageDTO orderBookPageDTO = new OrderBookPageDTO();

        log.info(" isbn >>> " + orders.getIsbn());

        Book book = bookRepository.findById(orders.getIsbn()).get();
        BookInfoDTO bookInfo = book.toBookInfo(); // DB에서 책 정보 불러오기

        if( book==null || bookInfo==null){
            return null;
        }

        if(orders.getIsbn()== book.getIsbn()){

            // select해온 책 정보에 구매수량 set
            bookInfo.setBookcount(orders.getBookcount());
            bookInfo.calPriceInfo();
        }

        // list에 추가
        orderBookPageDTO.addToBookInfoList(bookInfo);
        // 총액 합산
        orderBookPageDTO.calTotalprice();

        log.info("책 정보 >>>>>>>>>>>>> " + bookInfo);


        // 반환할 객체에 data 넣어주기
        //마일리지
        Mileage m = getCurrentMileage(userindex);
        orderBookPageDTO.setMileage(m.getMileage());

        log.info("Mileage >>> " + m.getMileage());

        //주소지
        AddrBook add = getUserAddress(userindex);
        orderBookPageDTO.setRecipient(add.getRecipient());
        orderBookPageDTO.setPhone(add.getPhone());
        orderBookPageDTO.setPostnum(add.getPostnum());
        orderBookPageDTO.setAddress1(add.getAddress1());
        orderBookPageDTO.setAddress2(add.getAddress2());

        log.info("AddrBook >>> " + add);

        log.info("view order >>> " + orderBookPageDTO);

        return orderBookPageDTO;
    }

    public List<Book> getbooks(List<String> isbns){

        return  bookRepository.findByIsbnIn(isbns);
    }

    public AddrBook getUserAddress(int userindex){

        // 저장된 배송정보
        // userindex, priority=1로 AddrBook select
        return addrBookRepository.findByUserindexAndPriority(userindex, 1);

    }

    public Mileage getCurrentMileage(int userindex){

        // 현재 마일리지
        // userindex로 마일리지 select

        Mileage mileage = mileageRepository.findByUserindex(userindex);

        if(mileage==null){

            Mileage m = Mileage.builder().userindex(userindex).mileage(0).build();
            mileage = mileageRepository.save(m);

        }

        return mileage;

    }


}


