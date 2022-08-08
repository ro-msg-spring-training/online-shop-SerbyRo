package ro.msg.learning.shop.model;


import lombok.*;

import javax.persistence.*;

@Entity
@IdClass(OrderProductId.class)
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Table(name="product_order_detail")
@EqualsAndHashCode(exclude = {"order", "product"})

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

    public ProductOrderDetail(ProductOrder placeOrder, Product foundProduct, Integer quantity) {
        this.order = placeOrder;
        this.product=foundProduct;
        this.quantity=quantity;
    }
}
