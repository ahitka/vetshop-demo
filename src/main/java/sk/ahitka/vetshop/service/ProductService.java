package sk.ahitka.vetshop.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk.ahitka.vetshop.controller.dto.ProductDto;
import sk.ahitka.vetshop.controller.dto.ProductExtendedDto;

public interface ProductService {

    Page<ProductDto> findAll(String searchCriteria, Pageable pageable);

    @NonNull ProductExtendedDto findById(Long id);
}
