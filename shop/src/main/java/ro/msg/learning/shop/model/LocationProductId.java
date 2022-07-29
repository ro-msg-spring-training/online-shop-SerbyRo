package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LocationProductId implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "location_id")
    private Long locationId;
}
