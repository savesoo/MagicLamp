package com.app.magiclamp.model.mypage;

import com.app.magiclamp.entity.Mileage;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MileageSpecification {

        public static Specification<Mileage> equalUserindex(int userindex) {
            return new Specification<Mileage>() {
                @Override
                public Predicate toPredicate(Root<Mileage> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    // 1) equal
                    return criteriaBuilder.equal(root.get("userindex"), userindex);
                }
            };
        }

        public static Specification<Mileage> equalMileage() {
                return new Specification<Mileage>() {
                    @Override
                    public Predicate toPredicate(Root<Mileage> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                        return criteriaBuilder.equal(root.get("usemileage"), "0");
                    }
                };
        }

    public static Specification<Mileage> equalUseMileage() {
            return new Specification<Mileage>() {
                @Override
                public Predicate toPredicate(Root<Mileage> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.equal(root.get("mileage"), "0");
                }
            };

    }

}
