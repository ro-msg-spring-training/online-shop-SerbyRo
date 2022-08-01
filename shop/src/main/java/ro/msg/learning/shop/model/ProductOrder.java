package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="product_order")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="shippedFrom_id")
    private Location location;

    @OneToMany(mappedBy = "order")
    private Set<ProductOrderDetail> orderDetails;

    private String address_country;
    private String address_city;
    private String address_county;
    private String address_street_address;
}
