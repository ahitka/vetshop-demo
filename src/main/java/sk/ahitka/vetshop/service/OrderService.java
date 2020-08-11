package sk.ahitka.vetshop.service;

import sk.ahitka.vetshop.controller.dto.CreateOrderItemDto;
import sk.ahitka.vetshop.domain.model.ProductsOrder;
import lombok.NonNull;

import java.util.List;

public interface OrderService {

    @NonNull ProductsOrder createOrder(@NonNull String username, @NonNull List<CreateOrderItemDto> items);

    @NonNull List<ProductsOrder> getOrdersForUser(@NonNull String userName);
}
