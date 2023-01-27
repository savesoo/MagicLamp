package com.app.magiclamp.service.mypage;

import com.app.magiclamp.entity.Mileage;
import com.app.magiclamp.mapper.MyPageReviewMapper;
import com.app.magiclamp.model.mypage.MileageListPage;
import com.app.magiclamp.model.mypage.MileageSpecification;
import com.app.magiclamp.repository.MileageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class MileageListService {

    @Autowired
    private MileageRepository mileageRepository;

    @Autowired
    private MyPageReviewMapper mypageMapper;

    public MileageListPage getMileagePage(int userindex, int pageNum, int keyword){

        Specification<Mileage> spec = Specification.where(MileageSpecification.equalUserindex(userindex));

        if(keyword == 1){
            spec = spec.and(MileageSpecification.equalMileage());
        }
        else if(keyword == 2){
            spec = spec.and(MileageSpecification.equalUseMileage());
        }
            Pageable pageable = PageRequest.of(pageNum-1, 10, Sort.by("userindex").descending().and(Sort.by("mileageindex")));
            Page<Mileage> page = mileageRepository.findAll(spec, pageable);
            List<Mileage> list = page.getContent();


            log.info(list);

            int totalCount = (int) page.getTotalElements();

            Map<String,Integer> totalMile = mypageMapper.selectTotalMileage(userindex);
            int totalUse = Integer.parseInt(String.valueOf(totalMile.get("totalUse")));
            int totalSave = Integer.parseInt(String.valueOf(totalMile.get("totalSave")));
            int totalExpired = mypageMapper.expiredMileage(userindex);

            log.info("###################" + totalUse);
            log.info("###################" + totalSave);

            MileageListPage mileageListPage = new MileageListPage(10, pageNum, list, totalCount, totalUse, totalSave, totalExpired);

        return mileageListPage;

    }

}
