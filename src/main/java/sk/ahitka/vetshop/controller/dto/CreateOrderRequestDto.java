package sk.ahitka.vetshop.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;


@Data
@NoArgsConstructor
public class CreateOrderRequestDto {
    @NonNull
    private List<CreateOrderItemDto> items;
}
