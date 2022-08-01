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
@Table(name="product_order_detail")
public class ProductOrderDetail {
    @Id
    @Column(name = "product_order_id")
    private Long orderId;
    @Id
    private Long productId;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name ="product_order_id")
    private ProductOrder order;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    private Integer quantity;
}
