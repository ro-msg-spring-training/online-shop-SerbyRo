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
    //@JoinColumn(name = "location_id", insertable=false, updatable=false)
    private Location location;

    @ManyToOne
    @MapsId("productId")
    //@JoinColumn(name = "product_id", insertable=false, updatable=false)
    private Product product;

    private Integer quantity;
}
