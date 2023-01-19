package com.app.magiclamp;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.entity.Mileage;
import com.app.magiclamp.entity.Order;
import com.app.magiclamp.model.order.RequestOrderBook;
import com.app.magiclamp.model.order.RequestPaymentBook;
import com.app.magiclamp.repository.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Log4j2
public class LampTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookAdministratorRepository administratorRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddrBookRepository addrBookRepository;

    @Autowired
    private MileageRepository mileageRepository;

    @Test
    public void entityTest(){


        /*Book book = bookRepository.findById("9788942104970").get();
        log.info(book);*/

        List<Book> list = bookRepository.findAll();

        log.info(list);

        for(Book book : list){
            log.info("book >>> " + book);
        }
    }

    @Test
    public void orderTest(){

        List<Order> list = orderRepository.findAll();

        for(Order order : list){
            log.info("order >>> " + order);
        }


    }


    /*@Test
    public void saveBookTest(){

        Book book = Book.builder()
                .isbn("9788962818913")
                .author("히가시노 사토미 (東野さとみ)")
                .binding("HB")
                .bookimg("https://image.aladin.co.kr/product/30682/31/cover500/8962818914_1.jpg")
                .category("대학교재")
                .contents("회화 전문 교재")
                .description("반복 연습")
                .mileagerate(5)
                .pages(188)
                .price(17000)
                .pubdate("2019-08-11")
                .publisher("파고다")
                .saleprice(15300)
                .size("188*257mm")
                .status("정상")
                .stock(132)
                .title("스쿠스쿠 일본어 회화 2")
                .weight(357)
                .build();

        log.info("insert >>> " + administratorRepository.save(book));

    }*/


    /*@Test
    public void insertAddress(){

        AddrBook add = AddrBook.builder()
                .userindex(1)
                .addrname("배송지")
                .recipient("나")
                .phone("01044448888")
                .postnum("12345")
                .address1("서울시 중구")
                .address2("상세주소2")
                .build();

        log.info("insert address >>> " + addrBookRepository.save(add));

    }*/


    @Test
    public void getSelectBook(){

       /* RequestOrderPagebook isbn = RequestOrderPagebook.builder()
                .isbn("9788962818911").build();*/

        String isbn = "9788962818911";

        RequestOrderBook rob = RequestOrderBook.builder().isbn(isbn).build();


        List<String> isbns = new ArrayList<>();
        isbns.add(isbn);
        isbns.add(isbn);

        log.info("check1" + rob);

        log.info("check2" + orderRepository.findByIsbn(isbn));

        List<Book> bookList = bookRepository.findByIsbnIn(isbns);
        log.info("check2 " + bookList.isEmpty());
        log.info(bookList);

    }

    /*@Test
    public void calStock(){

        RequestPaymentBook paymentBook = RequestPaymentBook.builder()
                .isbn("9788901249605")
                .bookcount(1).build();

        Book book = bookRepository.findById(paymentBook.getIsbn()).get();
        book.setStock(book.getStock()-paymentBook.getBookcount());

        log.info("stock calculate >>> " + bookRepository.updateStockByIsbn(book.getStock(), paymentBook.getIsbn()));

    }*/

    @Test
    public void calMil(){

        RequestPaymentBook paymentBook = RequestPaymentBook.builder()
                .isbn("9788901249605")
                .mileage(50000)
                .saveMileage(1100)
                .build();

        int userindex = 1;

        //mileageRepository.findMileageByUserindex(userindex).getMileage();
        int resultMileagy = paymentBook.getMileage(); // 현재 마일리지
        resultMileagy = resultMileagy + paymentBook.getSaveMileage(); // 현 - 사용 + 적립 연산 필요

        mileageRepository.updateMileageAndUpdatedateByUserindex(resultMileagy, LocalDate.now(), userindex);

        log.info("saved mil >>> "+ resultMileagy);

    }

}