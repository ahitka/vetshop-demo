package sk.ahitka.vetshop.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Collection;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection(targetClass=AnimalCategory.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="product_animal_categories")
    @Column(name="animal_category_name")
    private Collection<AnimalCategory> animalCategories;

    private BigDecimal price;

    private String description;

    @ElementCollection
    @CollectionTable(name="product_gallery")
    @Column(name="image_url")
    private Collection<URL> urlList;
}
