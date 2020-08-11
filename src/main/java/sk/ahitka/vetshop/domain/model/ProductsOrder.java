package sk.ahitka.vetshop.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ProductsOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCTS_ORDER_ID")
    private List<OrderItem> items = new ArrayList<>();

    private LocalDateTime orderCreatedAt;

    private String username;

    @Transient
    public BigDecimal getTotalPrice() {
        BigDecimal sum = BigDecimal.ZERO;
        List<OrderItem> items = getItems();
        for (OrderItem item : items) {
            sum = sum.add(item.getPriceAtPurchase().multiply(BigDecimal.valueOf(item.getCount())));
        }
        return sum;
    }
}
