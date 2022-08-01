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
@Table(name="stock")
public class Stock {
    @Id

    private Long productId;

    @Id
    private Long locationId;

    @ManyToOne
    @MapsId("locationId")

    private Location location;

    @ManyToOne
    @MapsId("productId")

    private Product product;

    private Integer quantity;

    public Stock(Location location, Product product, Integer quantity) {
        this.location = location;
        this.product=product;
        this.quantity=quantity;
    }
}
