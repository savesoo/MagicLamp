package com.app.magiclamp.model.mypage;

import com.app.magiclamp.entity.AddrBook;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AddrBookSpecification {

        public static Specification<AddrBook> equalUserindex(int userindex) {
            return new Specification<AddrBook>() {
                @Override
                public Predicate toPredicate(Root<AddrBook> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    // 1) equal
                    return criteriaBuilder.equal(root.get("userindex"), userindex);
                }
            };
        }

        public static Specification<AddrBook> likeRecipient(String recipient) {
            return new Specification<AddrBook>() {
                @Override
                public Predicate toPredicate(Root<AddrBook> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.like(root.get("recipient"), "%" + recipient + "%");
                }
            };
        }
}
