package com.app.magiclamp.service.order;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.entity.Book;
import com.app.magiclamp.entity.User;
import com.app.magiclamp.mapper.MileageMapper;
import com.app.magiclamp.model.book.BookInfoDTO;
import com.app.magiclamp.model.order.RequestOrderBook;
import com.app.magiclamp.model.order.OrderBookPageDTO;
import com.app.magiclamp.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class OrderPageViewService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AddrBookRepository addrBookRepository;
    @Autowired
    private MileageMapper mileageMapper;

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
        int m = getCurrentMileage(userindex);
        orderBookPageDTO.setMileage(m);

        log.info("Mileage >>> " + m);

        //주소지
        List<AddrBook> add = getUserAddressList(userindex);
        orderBookPageDTO.setAddrs(add);
//        AddrBook add = getUserAddress(userindex);
//        orderBookPageDTO.setRecipient(add.getRecipient());
//        orderBookPageDTO.setPhone(add.getPhone());
//        orderBookPageDTO.setPostnum(add.getPostnum());
//        orderBookPageDTO.setAddress1(add.getAddress1());
//        orderBookPageDTO.setAddress2(add.getAddress2());

        log.info("AddrBook >>> " + add);

        log.info("view order >>> " + orderBookPageDTO);

        return orderBookPageDTO;
    }

    // 장바구니에서 책 정보 넘길 때
    @Transactional
    public OrderBookPageDTO getOrderBookPost(List<RequestOrderBook> orders, int userindex) {

        OrderBookPageDTO orderBookPageDTO = new OrderBookPageDTO();
        List<BookInfoDTO> bookinfos = new ArrayList<>();
        for (RequestOrderBook order : orders){
            log.info("###############service isbn >> " + order.getIsbn());
            Book book = bookRepository.findById(order.getIsbn()).get();
            BookInfoDTO bookInfo = book.toBookInfo();

            log.info("###############service book  >> " + book);

            if(order.getIsbn() == book.getIsbn()){
                // select해온 책 정보에 구매수량 set
                bookInfo.setBookcount(order.getBookcount());
                bookInfo.calPriceInfo();
            }

            if(bookInfo == null || book == null){
                return null;
            }

            log.info("###############service bookinfo  >> " + bookInfo);

            bookinfos.add(bookInfo);
            log.info("###############service bookinfos >>>>>>>>>>>>> " + bookinfos);
        }

        orderBookPageDTO.addToBookInfos(bookinfos);

        // 총액 합산
        orderBookPageDTO.calTotalprice();

        // 반환할 객체에 data 넣어주기
        //마일리지

        int m = getCurrentMileage(userindex);
        orderBookPageDTO.setMileage(m);

        log.info("Mileage >>> " + m);

        //주소지
        List<AddrBook> add = getUserAddressList(userindex);
        orderBookPageDTO.setAddrs(add);
//        orderBookPageDTO.setRecipient(add.getRecipient());
//        orderBookPageDTO.setPhone(add.getPhone());
//        orderBookPageDTO.setPostnum(add.getPostnum());
//        orderBookPageDTO.setAddress1(add.getAddress1());
//        orderBookPageDTO.setAddress2(add.getAddress2());


        log.info("AddrBook >>> " + add);

        log.info("view order >>> " + orderBookPageDTO);

        return orderBookPageDTO;
    }

    public AddrBook getUserAddress(int userindex){

        // 저장된 배송정보
        // userindex, priority=1로 AddrBook select
        AddrBook add = addrBookRepository.findByUserindexAndPriority(userindex, 1);
        User user = userRepository.findById(userindex).get();

        // null일 때 회원가입시 입력받은 주소로 기본 배송지 생성
        if(add==null){

            AddrBook registAdd = AddrBook.builder()
                    .userindex(userindex)
                    .recipient(user.getUsername())
                    .phone(user.getPhone())
                    .postnum(user.getPostnum())
                    .address1(user.getAddress1())
                    .address2(user.getAddress2())
                    .priority(1)
                    .build();

            add = addrBookRepository.save(registAdd);
        }

        return add;

    }

    public List<AddrBook> getUserAddressList(int userindex){

        // 저장된 배송정보
        // userindex, priority=1로 AddrBook select
//        AddrBook add = addrBookRepository.findByUserindexAndPriority(userindex, 1);
        List<AddrBook> add = addrBookRepository.findListByUserIndex(userindex);
        User user = userRepository.findById(userindex).get();

//        // null일 때 회원가입시 입력받은 주소로 기본 배송지 생성
//        if(add==null){
//
//            AddrBook registAdd = AddrBook.builder()
//                    .userindex(userindex)
//                    .recipient(user.getUsername())
//                    .phone(user.getPhone())
//                    .postnum(user.getPostnum())
//                    .address1(user.getAddress1())
//                    .address2(user.getAddress2())
//                    .priority(1)
//                    .build();
//
//            add = addrBookRepository.save(registAdd);
//        }

        return add;

    }

    public int getCurrentMileage(int userindex){

        // 현재 마일리지
        // userindex로 마일리지 select

        Map<String,Integer> currMileage  = mileageMapper.selectCurrentMileage(userindex);
        int totalMileage = Integer.parseInt(String.valueOf(currMileage.get("currMileage")));

        return totalMileage;

    }


}


