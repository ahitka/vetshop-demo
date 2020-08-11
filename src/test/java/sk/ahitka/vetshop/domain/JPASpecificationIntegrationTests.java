package sk.ahitka.vetshop.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import sk.ahitka.vetshop.VetshopApplication;
import sk.ahitka.vetshop.domain.model.AnimalCategory;
import sk.ahitka.vetshop.domain.model.Product;
import sk.ahitka.vetshop.domain.repository.ProductRepository;
import sk.ahitka.vetshop.domain.specification.ProductSpecification;
import sk.ahitka.vetshop.domain.specification.SearchOperation;
import sk.ahitka.vetshop.domain.specification.SpecSearchCriteria;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VetshopApplication.class)
public class JPASpecificationIntegrationTests {

    @Autowired
    private ProductRepository productRepository;

    private Product catProduct;

    private Product dogProduct;

    @Before
    public void init() {
        productRepository.deleteAll();

        catProduct = new Product();
        catProduct.setName("Cat Product");
        catProduct.setAnimalCategories(Arrays.asList(AnimalCategory.CATS));
        catProduct.setDescription("desc cat");
        catProduct.setPrice(BigDecimal.ONE);
        catProduct = productRepository.save(catProduct);

        dogProduct = new Product();
        dogProduct.setName("Dog Product");
        dogProduct.setAnimalCategories(Arrays.asList(AnimalCategory.DOGS));
        dogProduct.setDescription("desc pes");
        dogProduct.setPrice(BigDecimal.TEN);
        dogProduct = productRepository.save(dogProduct);
    }

    @Test
    public void givenNameStartLetterC_whenGettingProducts_thenCorrect() {
        ProductSpecification spec = new ProductSpecification(new SpecSearchCriteria("name", SearchOperation.STARTS_WITH, "C"));
        List<Product> results = productRepository.findAll(Specification.where(spec));
        assertEquals("Expected result size 1", 1, results.size());
        assertEquals("Expected cat product", true, results.contains(catProduct));
        assertEquals("Not Expected Dog product", false, results.contains(dogProduct));
    }

    @Test
    public void givenPriceEquals_whenGettingProducts_thenCorrect() {
        ProductSpecification priceSpec = new ProductSpecification(new SpecSearchCriteria("price", SearchOperation.EQUALITY, BigDecimal.TEN));
        List<Product> results = productRepository.findAll(Specification.where(priceSpec));
        assertEquals("Expected result size 1", 1, results.size());
        assertEquals("Expected DOG product", true, results.contains(dogProduct));
    }


    @Test
    public void givenMinPrice_whenGettingProducts_thenCorrect() {
        ProductSpecification spec = new ProductSpecification(new SpecSearchCriteria("price", SearchOperation.GREATER_THAN, BigDecimal.valueOf(2)));
        List<Product> results = productRepository.findAll(Specification.where(spec));
        assertEquals("Expected result size 1", 1, results.size());
        assertEquals("Expected DOG product", true, results.contains(dogProduct));
    }


    @Test
    public void givenMaxPrice_whenGettingProducts_thenCorrect() {
        ProductSpecification spec = new ProductSpecification(new SpecSearchCriteria("price", SearchOperation.LESS_THAN, BigDecimal.valueOf(2)));
        List<Product> results = productRepository.findAll(Specification.where(spec));
        assertEquals("Expected result size 1", 1, results.size());
        assertEquals("Expected CAT product", true, results.contains(catProduct));
    }

    @Test
    public void givenPriceRange_whenGettingProducts_thenCorrect() {
        Product horseProduct = new Product();
        horseProduct.setPrice(BigDecimal.valueOf(5));
        horseProduct = productRepository.save(horseProduct);
        ProductSpecification minSpec = new ProductSpecification(new SpecSearchCriteria("price", SearchOperation.GREATER_THAN, BigDecimal.ONE));
        ProductSpecification maxSpec = new ProductSpecification(new SpecSearchCriteria("price", SearchOperation.LESS_THAN, BigDecimal.TEN));
        List<Product> results = productRepository.findAll(Specification.where(minSpec).and(maxSpec));
        assertEquals("Expected result size 1", 1, results.size());
        assertEquals("Expected Horse product", true, results.contains(horseProduct));
    }

    @Test
    public void givenNameAndMaxPrice_whenGettingProducts_thenCorrect() {
        Product dogProduct2 = new Product();
        dogProduct2.setName("Dog Product 2");
        dogProduct2.setPrice(BigDecimal.valueOf(5));
        dogProduct2 = productRepository.save(dogProduct2);
        ProductSpecification nameSpec = new ProductSpecification(new SpecSearchCriteria("name", SearchOperation.STARTS_WITH, "D"));
        ProductSpecification maxSpec = new ProductSpecification(new SpecSearchCriteria("price", SearchOperation.LESS_THAN, BigDecimal.TEN));
        List<Product> results = productRepository.findAll(Specification.where(nameSpec).and(maxSpec));
        assertEquals("Expected result size 1", 1, results.size());
        assertEquals("Expected Horse product", true, results.contains(dogProduct2));
    }
}
