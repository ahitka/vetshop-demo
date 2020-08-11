package sk.ahitka.vetshop.mapper;

import sk.ahitka.vetshop.controller.dto.OrderItemDto;
import sk.ahitka.vetshop.controller.dto.ProductExtendedDto;
import sk.ahitka.vetshop.controller.dto.ProductsOrderDto;
import sk.ahitka.vetshop.domain.model.OrderItem;
import sk.ahitka.vetshop.domain.model.ProductsOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<ProductsOrderDto> fromEntityList(List<ProductsOrder> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream().map(productsOrder -> fromEntity(productsOrder)).collect(Collectors.toList());
    }


    public ProductsOrderDto fromEntity(ProductsOrder entity) {
        if (entity == null) {
            return null;
        }

        ProductsOrderDto dto = new ProductsOrderDto();
        dto.setId(entity.getId());
        dto.setOrderCreatedAt(entity.getOrderCreatedAt());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setItems(entity.getItems().stream().map(orderItem -> mapOrderItemFromEntity(orderItem)).collect(Collectors.toList()));

        return dto;
    }

    public OrderItemDto mapOrderItemFromEntity(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        OrderItemDto dto = new OrderItemDto();
        dto.setCount(orderItem.getCount());
        dto.setId(orderItem.getId());
        dto.setPriceAtPurchase(orderItem.getPriceAtPurchase());
        dto.setProduct(modelMapper.map(orderItem.getProduct(), ProductExtendedDto.class));
        return dto;
    }
}
