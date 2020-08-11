package sk.ahitka.vetshop.controller.dto;

import sk.ahitka.vetshop.domain.model.AnimalCategory;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Collection<AnimalCategory> animalCategories;
    private BigDecimal price;
}
