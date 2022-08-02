package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="product_order")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(exclude = "customer")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name="shipped_from_id")
    private Location location;

    @OneToMany(mappedBy = "order")
    private Set<ProductOrderDetail> orderDetails;

    private String address_country;
    private String address_city;
    private String address_county;
    private String address_street_address;
}
