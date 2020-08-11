package sk.ahitka.vetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ahitka.vetshop.controller.dto.ProductDto;
import sk.ahitka.vetshop.controller.dto.ProductExtendedDto;
import sk.ahitka.vetshop.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findPaginated(@RequestParam(value = "search", required = false) String search, Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(search, pageable));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductExtendedDto> getProductById(@PathVariable String id){
        return ResponseEntity.ok(productService.findById(Long.parseLong(id)));
    }
}