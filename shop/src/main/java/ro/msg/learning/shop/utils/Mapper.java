package ro.msg.learning.shop.utils;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.*;
import ro.msg.learning.shop.model.*;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Mapper {
    public ProductCategoryDto toProductCategoryDto(ProductCategory productCategory)
    {
        return ProductCategoryDto.builder().
                categoryId(productCategory.getId())
                .name(productCategory.getName())
                .description(productCategory.getDescription())
                .build();
    }

    public ProductCategory toProductCategory(ProductCategoryDto productCategoryDto)
    {
        ProductCategory newProductCategory = ProductCategory.builder()
                .name(productCategoryDto.getName())
                .description(productCategoryDto.getDescription())
                .build();
        newProductCategory.setId(productCategoryDto.getCategoryId());
        return newProductCategory;
    }
    public SupplierDto toSupplierDto(Supplier supplier)
    {
        return SupplierDto.builder()
                .supplierId(supplier.getId())
                .name(supplier.getName()).build();
    }
    public Supplier toSupplier(SupplierDto supplierDto)
    {
        Supplier newSupplier = Supplier.builder()
                .name(supplierDto.getName()).build();
        newSupplier.setId(supplierDto.getSupplierId());
        return newSupplier;
    }

    public CustomerDto toCustomerDto(Customer customer)
    {
        return CustomerDto.builder()
                .customerId(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .email(customer.getEmail()).build();
    }
    public Customer toCustomer(CustomerDto customerDto)
    {
        Customer newCustomer = Customer.builder()
                .username(customerDto.getUsername())
                .password(customerDto.getPassword())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail()).build();
        newCustomer.setId(customerDto.getCustomerId());
        return newCustomer;
    }

    public StockDto toStockDto(Stock stock)
    {
        return StockDto.builder()
                .locationName(stock.getLocation().getName())
                .productName(stock.getProduct().getName())
                .quantity(stock.getQuantity())
                .build();
    }

    public List<StockDto> toStocksList(List<Stock> stocksList)
    {
        List<StockDto> stockDtos = new ArrayList<>();
        for (Stock createdStocks: stocksList)
        {
            stockDtos.add(toStockDto(createdStocks));
        }
        return stockDtos;
    }

    public LocationDto toLocationDto(Location location)
    {
        return LocationDto.builder()
                .name(location.getName())
                .addressCountry(location.getAddress_country())
                .addressCounty(location.getAddress_county())
                .addressCity(location.getAddress_city())
                .addressStreet(location.getAddress_streetAddress()).build();
    }



    public Location toLocation(LocationDto locationDto)
    {
        Location newLocation = Location.builder()
                .name(locationDto.getName())
                .address_country(locationDto.getAddressCountry())
                .address_county(locationDto.getAddressCounty())
                .address_city(locationDto.getAddressCity())
                .address_streetAddress(locationDto.getAddressStreet()).build();
        newLocation.setId(locationDto.getLocationId());
        return newLocation;
    }

    public OrderDetailDto toOrderDetailDto(ProductOrderDetail productOrderDetail)
    {
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setProductId(productOrderDetail.getProduct().getId());
        orderDetailDto.setQuantity(orderDetailDto.getQuantity());
        return orderDetailDto;
    }

    public ProductOrderDetail toproductOrderDetail(OrderDetailDto orderDetailDto)
    {
        Product product = new Product();
        product.setId(orderDetailDto.getProductId());

        ProductOrderDetail productOrderDetail = new ProductOrderDetail();
        productOrderDetail.setProduct(product);
        productOrderDetail.setQuantity(orderDetailDto.getQuantity());
        return productOrderDetail;
    }

    public List<OrderDetailDto> toListOrderDetailDto(List<ProductOrderDetail> orderDetailList)
    {
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        for (ProductOrderDetail productOrderDetail:orderDetailList)
        {
            OrderDetailDto orderDetailDto = toOrderDetailDto(productOrderDetail);
            orderDetailDto.setProductId(productOrderDetail.getProductId());
            orderDetailDtos.add(orderDetailDto);
        }
        return orderDetailDtos;
    }

    public List<ProductOrderDetail> toListOrderDetail(List<OrderDetailDto> orderDetailDtos)
    {
        List<ProductOrderDetail> orderDetailList = new ArrayList<>();
        for(OrderDetailDto orderDetailDto : orderDetailDtos)
        {
            orderDetailList.add(toproductOrderDetail(orderDetailDto));
        }
        return orderDetailList;
    }

    public OrderDto toOrderDto(ProductOrder productOrder){
        OrderDto orderDto = new OrderDto();
        orderDto.setProductOrders(toListOrderDetailDto(productOrder.getOrderDetails()));
        orderDto.setCreatedTime(productOrder.getCreatedAt());
        orderDto.setDeliveryAddress(new AddressDto(productOrder.getAddress_country(), productOrder.getAddress_county(), productOrder.getAddress_city(), productOrder.getAddress_street_address()));
        return orderDto;
    }

    public ProductOrder toProductOrder(OrderDto orderDto)
    {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrderDetails(toListOrderDetail(orderDto.getProductOrders()));
        productOrder.setCreatedAt(orderDto.getCreatedTime());
        productOrder.setAddress_country(orderDto.getDeliveryAddress().getAddressCountry());
        productOrder.setAddress_county(orderDto.getDeliveryAddress().getAddressCounty());
        productOrder.setAddress_city(orderDto.getDeliveryAddress().getAddressCity());
        productOrder.setAddress_street_address(orderDto.getDeliveryAddress().getAddressStreet());
        return productOrder;
    }



    public AddressDto toAddressDto(String addressCountry,String addressCounty,String addressCity,String addressStreet)
    {
        return new AddressDto(addressCountry,addressCounty,addressCity,addressStreet);
    }

    public ProductCombinedDto toProductDto(Product product)
    {
        return ProductCombinedDto.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .weight(product.getWeight())
                .imageUrl(product.getImageUrl())
                .supplier(this.toSupplierDto(product.getSupplier()))
                .category(this.toProductCategoryDto(product.getProductCategory()))
                .build();
    }

    public Product toProduct(ProductCombinedDto productCombinedDto)
    {
        Product newProduct = Product.builder()
                .name(productCombinedDto.getName())
                .description(productCombinedDto.getDescription())
                .price(productCombinedDto.getPrice())
                .weight(productCombinedDto.getWeight())
                .imageUrl(productCombinedDto.getImageUrl())
                .supplier(this.toSupplier(productCombinedDto.getSupplier()))
                .productCategory(this.toProductCategory(productCombinedDto.getCategory()))
                .build();
        newProduct.setId(productCombinedDto.getProductId());
        return newProduct;
    }
}
