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
public class OrderProductId implements Serializable {
    @Column(name = "product_order_id")
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;
}



