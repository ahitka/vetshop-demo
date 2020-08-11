package sk.ahitka.vetshop.controller;

import sk.ahitka.vetshop.controller.dto.CreateOrderRequestDto;
import sk.ahitka.vetshop.controller.dto.ProductsOrderDto;
import sk.ahitka.vetshop.mapper.OrderMapper;
import sk.ahitka.vetshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/create")
    public void createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderService.createOrder(user.getUsername(), createOrderRequestDto.getItems());
    }

    @GetMapping
    public List<ProductsOrderDto> getOrdersForUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ProductsOrderDto> result = orderMapper.fromEntityList(orderService.getOrdersForUser(user.getUsername()));
        return result;
    }
}