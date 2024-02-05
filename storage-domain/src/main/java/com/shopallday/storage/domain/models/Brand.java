package com.shopallday.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Brand {
    private Long brandId;
    private String brandName;
}
