package com.barriento.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Offer {
    private String shopName;
    private String shopLink;
    private Double price;
    private String discountCode;
}
