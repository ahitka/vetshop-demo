package sk.ahitka.vetshop.controller.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long id;

    @NonNull
    private ProductExtendedDto product;

    @NonNull
    private Integer count;

    private BigDecimal priceAtPurchase;
}
