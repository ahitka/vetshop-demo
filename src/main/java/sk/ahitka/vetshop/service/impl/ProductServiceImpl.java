package sk.ahitka.vetshop.service.impl;

import com.google.common.base.Joiner;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sk.ahitka.vetshop.controller.dto.ProductDto;
import sk.ahitka.vetshop.controller.dto.ProductExtendedDto;
import sk.ahitka.vetshop.domain.model.Product;
import sk.ahitka.vetshop.domain.repository.ProductRepository;
import sk.ahitka.vetshop.domain.specification.ProductSpecificationBuilder;
import sk.ahitka.vetshop.service.ProductService;
import sk.ahitka.vetshop.domain.specification.SearchOperation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ProductDto> findAll(String searchCriteria, Pageable pageable) {
        ProductSpecificationBuilder builder = new ProductSpecificationBuilder();
        String operationSetExper = Joiner.on("|").join(SearchOperation.OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\p{Punct}?)(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(searchCriteria + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
        }
        Specification<Product> spec = builder.build();;
        return new PageImpl<>(productRepository.findAll(spec, pageable).stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList()));
    }

    @Override
    public @NonNull ProductExtendedDto findById(@NonNull Long id) {
        return modelMapper.map(productRepository.findById(id).orElseThrow(), ProductExtendedDto.class);
    }
}
