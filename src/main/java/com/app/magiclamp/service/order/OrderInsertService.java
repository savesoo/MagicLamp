package com.app.magiclamp.service.order;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.entity.Mileage;
import com.app.magiclamp.entity.Order;
import com.app.magiclamp.model.BookInfoDTO;
import com.app.magiclamp.model.order.OrderBookPageDTO;
import com.app.magiclamp.model.order.RequestPaymentBook;
import com.app.magiclamp.repository.BookRepository;
import com.app.magiclamp.repository.CartRepository;
import com.app.magiclamp.repository.MileageRepository;
import com.app.magiclamp.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class OrderInsertService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MileageRepository mileageRepository;

    @Transactional
    public int Order(RequestPaymentBook paymentBook, int userindex){

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
            book.setStock(book.getStock()-paymentBook.getBookcount());
            bookRepository.updateStockByIsbn(book.getStock(), paymentBook.getIsbn());

            log.info(" stock update >>> " + book.getStock());

        }

        // paymentBook setting 해줘야함
        paymentBook.setRealprice(paymentBook.getOrderTotalPrice()-paymentBook.getUsemileage());

        log.info(" payment check >>>>>>>>>>>>>>>>> " + paymentBook);

        Order order = paymentBook.toOrderEntity();

        // DB에 주문 등록
        result = orderRepository.save(order) !=null ? 1 : 0;

        log.info(" result >>> " + result);


        // 차감된 마일리지 계산, 반영
        //(현재-사용)+적립
        int resultMileagy = (paymentBook.getMileage() - paymentBook.getUsemileage()) + paymentBook.getTotalSaveMileage();

        Mileage mileage = mileageRepository.findByUserindex(userindex);
        mileage.setUsemileage(paymentBook.getUsemileage()); // 사용한 마일리지 set
        mileage.setMileage(resultMileagy);
        mileage.setUpdatedate(LocalDate.now());

        mileageRepository.save(mileage);

        log.info(" save mileage >>> " + mileage);

        return result;

    }

}
