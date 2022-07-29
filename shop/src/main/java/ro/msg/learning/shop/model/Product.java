package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Table(name="product")
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
    private List<Stock> stocks;

    @OneToMany(mappedBy = "product")
    private  List<ProductOrderDetail>  orderDetails;

    public Product(String name, String description, BigDecimal price, Double weight, String imageUrl, ProductCategory productCategory, Supplier supplier) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.imageUrl = imageUrl;
        this.productCategory = productCategory;
        this.supplier = supplier;
    }
}
