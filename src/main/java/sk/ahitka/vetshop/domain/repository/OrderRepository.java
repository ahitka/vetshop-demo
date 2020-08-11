package sk.ahitka.vetshop.domain.repository;

import sk.ahitka.vetshop.domain.model.ProductsOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<ProductsOrder, Long> {
    List<ProductsOrder> findByUsername(@Param("username") String username);
}
