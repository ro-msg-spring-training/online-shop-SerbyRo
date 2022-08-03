package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Table(name="product")
@EqualsAndHashCode(exclude = "productCategory")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Double weight;

    private String imageUrl;


    @ManyToOne()
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @ManyToOne()
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private Set<Stock> stocks;

    @OneToMany(mappedBy = "product")
    private  Set<ProductOrderDetail>  orderDetails;

    public Product(String name, String description, BigDecimal price, Double weight, String imageUrl, ProductCategory productCategory, Supplier supplier) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.imageUrl = imageUrl;
        this.productCategory = productCategory;
        this.supplier = supplier;
    }

    public Product(Long productId) {
        this.id = productId;
    }
}
