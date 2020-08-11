package sk.ahitka.vetshop.controller.dto;

import lombok.Data;

import java.net.URL;
import java.util.Collection;

@Data
public class ProductExtendedDto extends ProductDto {
    private String description;
    private Collection<URL> urlList;
}
