package sk.ahitka.vetshop.controller.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductsOrderDto {
    private Long id;
    private List<OrderItemDto> items = new ArrayList<>();
    private LocalDateTime orderCreatedAt;
    private BigDecimal totalPrice;
}
