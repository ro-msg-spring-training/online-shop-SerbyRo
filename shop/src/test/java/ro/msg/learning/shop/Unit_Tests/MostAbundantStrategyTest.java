package ro.msg.learning.shop.Unit_Tests;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.OnlineShopApplication;
import org.junit.runner.RunWith;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.ILocationInterfaceRepository;
import ro.msg.learning.shop.repository.IProductInterfaceRepository;
import ro.msg.learning.shop.repository.IStockInterfaceRepository;
import ro.msg.learning.shop.service.exceptions.LocationNotFound;
import ro.msg.learning.shop.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,classes = OnlineShopApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MostAbundantStrategyTest {
    @Mock
    private IProductInterfaceRepository productInterfaceRepository;

    @Mock
    private IStockInterfaceRepository stockInterfaceRepository;

    @Mock
    private ILocationInterfaceRepository locationInterfaceRepository;

    @InjectMocks
    private MostAbundantStrategy mostAbundantStrategy;


    ProductOrder productOrder;
    Product product;
    @Before
    public void initMostAbundantLocationStrategyTest(){
        MockitoJUnit.rule();
        ProductCategory productCategory = new ProductCategory("samsung","galaxy s22 smecher rau");
        productCategory.setId(1L);

        Supplier supplier = new Supplier("Service de telefoane");
        supplier.setId(1L);

        product = new Product("samsung","a7 2018",new BigDecimal(1900),34.2,supplier,productCategory,"samsungUrl");
        product.setId(1L);

        when(productInterfaceRepository.findById(product.getId())).thenReturn(Optional.of(product));

        Location location = new Location("location1","Romania","Cluj-Napoca","Cluj","Hasdeu 16");
        location.setId(1L);

        List<Location> locationList = new ArrayList<>();
        locationList.add(location);

        when(locationInterfaceRepository.findAll()).thenReturn(locationList);

        Stock stock =new Stock(location,product,2000);
        stock.setProductId(1L);
        stock.setLocationId(1L);

        productOrder = new ProductOrder("Romania","Cluj-Napoca","Floresti","Aleea centrala 45",location);
        productOrder.setId(1L);

        List<Stock> mockStock = new ArrayList<>();
        mockStock.add(stock);

        when(stockInterfaceRepository.findByProductId(product.getId())).thenReturn(mockStock);
    }

    @Test
    public void successRunningStrategy(){
        List<ProductOrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new ProductOrderDetail(productOrder,product,1000));

        List<Stock> stocks = mostAbundantStrategy.findBestLocation(orderDetails);

        assert (!stocks.isEmpty());
    }

    @Test(expected = LocationNotFound.class)
    public void failRunningStrategy(){
        List<ProductOrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new ProductOrderDetail(new ProductOrder(5L),new Product(5L),2));

        List<Stock> stocks = mostAbundantStrategy.findBestLocation(orderDetails);
    }


}
