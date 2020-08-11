package sk.ahitka.vetshop.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SearchProductsRequestDto {
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String nameStart;
}
