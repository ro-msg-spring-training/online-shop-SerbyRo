package ro.msg.learning.shop.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(LocationProductId.class)
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Stock {
    @Id
    private Long productId;

    @Id
    private Long locationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location")
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product")
    private Product product;

    private Integer quantity;
}
