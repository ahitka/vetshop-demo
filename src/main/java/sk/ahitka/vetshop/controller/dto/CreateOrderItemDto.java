package sk.ahitka.vetshop.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class CreateOrderItemDto {
    @NonNull
    private Long productId;

    @NonNull
    private Integer count;
}
