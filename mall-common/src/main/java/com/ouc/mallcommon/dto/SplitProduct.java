package com.ouc.mallcommon.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SplitProduct {
    private Integer id;

    private String productName;

    private String productDescription;

    private BigDecimal productPrice;

    private List<String> configuration;

    private List<String> color;

    private List<String> productImage;

    private String category;
}
