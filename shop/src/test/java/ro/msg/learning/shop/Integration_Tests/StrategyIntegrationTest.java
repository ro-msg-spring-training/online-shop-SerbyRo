package ro.msg.learning.shop.Integration_Tests;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ro.msg.learning.shop.Configuration.StrategyConfigIntegration;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.model.ProductOrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(StrategyConfigIntegration.class)
public class StrategyIntegrationTest {
    @Autowired
    @Qualifier("singleLocationStrategy")
    private SingleLocationStrategy singleLocationStrategy;

    private final List<ProductOrderDetail> productIdAndQuantityList =  new ArrayList<>();

    @BeforeEach
    public void setUp() {
        initOrderDetailList();
    }


    @Test
    void testGetStockForSingleLocation() {
        List<Stock> stocks = singleLocationStrategy.findBestLocation(productIdAndQuantityList);
        assertThat(stocks).hasSize(2);
        assertThat(stocks.get(0).getLocation().getId()).isEqualTo(stocks.get(1).getLocation().getId());

    }

    private void initOrderDetailList() {
        ProductOrderDetail orderDetail = ProductOrderDetail.builder()
                .product(new Product(1L))
                .order(new ProductOrder(1L))
                .quantity(3)
                .build();
        productIdAndQuantityList.add(orderDetail);
    }
}
