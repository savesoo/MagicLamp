package com.app.magiclamp.service.order;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.entity.Mileage;
import com.app.magiclamp.entity.Book;
import com.app.magiclamp.mapper.OrderMapper;
import com.app.magiclamp.model.BookInfoDTO;
import com.app.magiclamp.model.order.RequestOrderBook;
import com.app.magiclamp.model.order.OrderBookPageDTO;
import com.app.magiclamp.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;


    public OrderBookPageDTO getBooksInfo(
                                 List<RequestOrderBook> orders,
                                 int userindex){

        /**
         * [ {isbn1, count1}, {isbn2, count2} ... {isbn, count}];
         */


        String isbn = "9788962818911";

        List<String> isbns = new ArrayList<>();
        // isbns.add(isbn);
        for(RequestOrderBook orderBook : orders) {
            isbns.add(orderBook.getIsbn());
        }
        log.info("isbn >>> " + isbns);

        // isbn으로 선택한 책 select
        List<Book> bookList = bookRepository.findByIsbnIn(isbns);
        // bookList size check -> 0 일 때 처리 필요
        if(bookList==null | bookList.size()==0 | bookList.isEmpty()){
            return null;
        }

        OrderBookPageDTO requestOrder = new OrderBookPageDTO();

        // 사용자가 선택한 책의 종류와 수량, 마일리지, 주소 반환
        for(Book book : bookList) {

            // RequestOrderBook new -> 객체 생성
            // -> book으로 객체를 생성 -> select한 book에 대한 정보 저장
            BookInfoDTO bookInfo = book.toBookInfo();


            for( RequestOrderBook ob : orders ) {

                // 요청 정보와 저장된 isbn이 동일한 경우
                if(ob.getIsbn().equals(book.getIsbn())){
                    log.info(" isEqual?? >>> " + ob.getIsbn().equals(book.getIsbn()));

                    // 구매하고 싶은 수량 set
                    bookInfo.setBookcount(ob.getBookcount());
                    break;
                }
            }

            // 출력할 화면의 list에 추가
            // requestOrder.addBookInfo(bookInfo);
            /**
             * void addBookInfo(BookInfoDTO bookInfo) {
             *    ~~~list.add(bookInfo);
             * }
             */

        }

        //마일리지
        Mileage m = getCurrentMileage(userindex);
        requestOrder.setMileage(m.getMileage());

        log.info("Mileadge >>> " + m.getMileage());

        //주소지
        AddrBook add = getUserAddress(userindex);
        requestOrder.setRecipient(add.getRecipient());
        requestOrder.setPhone(add.getPhone());
        requestOrder.setPostnum(add.getPostnum());
        requestOrder.setAddress1(add.getAddress1());
        requestOrder.setAddress2(add.getAddress2());

        log.info("AddrBook >>> " + add);

        log.info("order >>> " + requestOrder);

        return requestOrder;

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

        return mileageRepository.findById(userindex).get();

    }


}


