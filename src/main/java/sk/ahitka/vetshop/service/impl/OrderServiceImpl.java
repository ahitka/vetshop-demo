package sk.ahitka.vetshop.service.impl;

import sk.ahitka.vetshop.controller.dto.CreateOrderItemDto;
import sk.ahitka.vetshop.domain.model.OrderItem;
import sk.ahitka.vetshop.domain.model.Product;
import sk.ahitka.vetshop.domain.model.ProductsOrder;
import sk.ahitka.vetshop.domain.repository.OrderRepository;
import sk.ahitka.vetshop.domain.repository.ProductRepository;
import sk.ahitka.vetshop.service.OrderService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductsOrder createOrder(@NonNull String username, @NonNull List<CreateOrderItemDto> items) {
        ProductsOrder productsOrder = new ProductsOrder();
        productsOrder.setItems(items.stream().map(createOrderItemDto -> prepareOrderItem(createOrderItemDto.getProductId(), createOrderItemDto.getCount())).collect(Collectors.toList()));
        productsOrder.setOrderCreatedAt(LocalDateTime.now());
        productsOrder.setUsername(username);
        return orderRepository.save(productsOrder);
    }

    @Override
    public @NonNull List<ProductsOrder> getOrdersForUser(@NonNull String userName) {
        List<ProductsOrder> orders = orderRepository.findByUsername(userName);
        return orders;
    }

    private OrderItem prepareOrderItem(@NonNull Long productId, @NonNull Integer count) {
        OrderItem orderItem = new OrderItem();
        Product product = productRepository.getOne(productId);
        orderItem.setProduct(product);
        orderItem.setPriceAtPurchase(product.getPrice());
        orderItem.setCount(count);
        return orderItem;
    }
}
