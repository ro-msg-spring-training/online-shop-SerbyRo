package ro.msg.learning.shop.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(OrderProductId.class)
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ProductOrderDetail {
    @Id
    private Long orderId;

    @Id
    private Long productId;

    @ManyToOne()
    @JoinColumn(name ="product_order" )
    private ProductOrder order;

    @ManyToOne()
    @JoinColumn(name = "product")
    private Product product;

    private Integer quantity;
}
