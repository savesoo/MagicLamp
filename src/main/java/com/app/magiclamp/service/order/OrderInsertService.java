package com.app.magiclamp.service.order;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.entity.Mileage;
import com.app.magiclamp.entity.Order;
import com.app.magiclamp.entity.OrderItem;
import com.app.magiclamp.model.BookInfoDTO;
import com.app.magiclamp.model.order.RequestPaymentBook;
import com.app.magiclamp.repository.BookRepository;
import com.app.magiclamp.repository.MileageRepository;
import com.app.magiclamp.repository.OrderItemRepository;
import com.app.magiclamp.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class OrderInsertService{
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MileageRepository mileageRepository;

    @Transactional
    public int Order(RequestPaymentBook paymentBook, int userindex) throws Exception{

        log.info(" order insert service ... ");

        int result = 0;

        // 주문할 책 정보
        List<BookInfoDTO> bookInfoDTO = paymentBook.getBookInfos();

        for(int i=0; i<bookInfoDTO.size(); i++) {

            log.info(" >>> is isbn exist? >>> " + bookInfoDTO.get(i).getIsbn());
            log.info(" >>> bookCount >>> " + bookInfoDTO.get(i).getBookcount());

            paymentBook.setIsbn(bookInfoDTO.get(i).getIsbn());
            paymentBook.setBookcount(bookInfoDTO.get(i).getBookcount());

            log.info(" get isbn >>> " + paymentBook.getIsbn());

            // 최신 재고에 반영
            Book book = bookRepository.findById(paymentBook.getIsbn()).get();

            // DB의 재고 < 요청 들어온 수량일 때
            if(book.getStock()-paymentBook.getBookcount() < 0){
                throw new Exception();
            }

            book.setStock(book.getStock()-paymentBook.getBookcount());
            bookRepository.updateStockByIsbn(book.getStock(), paymentBook.getIsbn());

            log.info(" stock update >>> " + book.getStock());

        }

        // paymentBook setting 해줘야함
        paymentBook.setRealprice(paymentBook.getOrderTotalPrice()-paymentBook.getUsemileage());

        log.info(" payment check >>>>>>>>>>>>>>>>> " + paymentBook);

        Order order = paymentBook.toOrderEntity();

        // DB에 주문 등록
//        result = orderRepository.save(order) !=null ? 1 : 0;

        order = orderRepository.save(order);

        for(int i=0; i<bookInfoDTO.size(); i++) {

            OrderItem.builder()
                    .orderindex(order.getOrderindex())
                    .isbn(bookInfoDTO.get(i).getIsbn())
                    .bookcount(bookInfoDTO.get(i).getBookcount())
                    .build();

            // DB에 주문한 아이템 등록
                result = orderItemRepository.save(
                        OrderItem.builder()
                        .orderindex(order.getOrderindex())
                        .isbn(bookInfoDTO.get(i).getIsbn())
                        .bookcount(bookInfoDTO.get(i).getBookcount())
                        .build()
                ) != null ? 1 : 0;

            log.info(" result >>> " + result);
        }

        // 사용한 마일리지 insert
        Mileage userMil = Mileage.builder().userindex(userindex).usemileage(paymentBook.getUsemileage()).build();
        mileageRepository.save(userMil);

        log.info(" used mileage >>> " + userMil);

        // 적립 마일리지 insert
        Mileage saveMil = Mileage.builder().userindex(userindex).mileage(paymentBook.getTotalSaveMileage()).build();
        mileageRepository.save(saveMil);

        log.info(" save mileage >>> " + saveMil);

        return result;

    }

}
