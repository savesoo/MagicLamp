package com.app.magiclamp.service.mypage;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.model.mypage.AddrBookListPage;
import com.app.magiclamp.model.mypage.AddrBookSpecification;
import com.app.magiclamp.repository.AddrBookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class AddrBookListService {

    @Autowired
    private AddrBookRepository addrBookRepository;

    public AddrBookListPage getAddrBookPage(int userindex, int pageNum, String recipient){

        Specification<AddrBook> spec = Specification.where(AddrBookSpecification.equalUserindex(userindex));

        if(!recipient.equals(" ") && recipient != null){
            spec = spec.and(AddrBookSpecification.likeRecipient(recipient));
        }

            Pageable pageable = PageRequest.of(pageNum-1, 10, Sort.by("priority").descending().and(Sort.by("addrindex")));
            Page<AddrBook> page = addrBookRepository.findAll(spec, pageable);
            List<AddrBook> list = page.getContent();

            log.info(list);

            int totalCount = (int) page.getTotalElements();

            AddrBookListPage addrBookListPage = new AddrBookListPage(10, pageNum, list, totalCount);

        return addrBookListPage;

    }

    public int addrnameChk(String addrname, int userindex){
        return addrBookRepository.addrnameChk(addrname, userindex);
    }

}
