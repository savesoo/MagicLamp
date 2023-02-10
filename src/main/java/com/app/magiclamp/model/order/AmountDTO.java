package com.app.magiclamp.model.order;

import lombok.Data;

@Data
public class AmountDTO {

    private Integer total, tax_free, vat, point, discount;

}
