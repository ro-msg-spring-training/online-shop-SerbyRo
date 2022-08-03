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
                .addressStreet(location.getAddress_street_address()).build();
    }



    public Location toLocation(LocationDto locationDto)
    {
        Location newLocation = Location.builder()
                .name(locationDto.getName())
                .address_country(locationDto.getAddressCountry())
                .address_county(locationDto.getAddressCounty())
                .address_city(locationDto.getAddressCity())
                .address_street_address(locationDto.getAddressStreet()).build();
        newLocation.setId(locationDto.getLocationId());
        return newLocation;
    }

    public OrderDetailDto toOrderDetailDto(OrderDetail orderDetail)
    {
        return OrderDetailDto.builder()
                .productId(orderDetail.getProductId())
                .quantity(orderDetail.getQuantity())
                .build();
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


    public List<ProductOrderDetail> toListOrderDetail(List<OrderDetailDto> orderDetailDtos)
    {
        List<ProductOrderDetail> orderDetailList = new ArrayList<>();
        for(OrderDetailDto orderDetailDto : orderDetailDtos)
        {
            orderDetailList.add(toproductOrderDetail(orderDetailDto));
        }
        return orderDetailList;
    }


    public ProductOrder toProductOrder(CreateOrderDto createOrderDto)
    {
        return ProductOrder.builder()
                .id(createOrderDto.getOrderId())
                .createdAt(createOrderDto.getCreatedTime())
                .address_country(createOrderDto.getDeliveryAddress().getAddressCountry())
                .address_city(createOrderDto.getDeliveryAddress().getAddressCity())
                .address_county(createOrderDto.getDeliveryAddress().getAddressCounty())
                .address_street_address(createOrderDto.getDeliveryAddress().getAddressStreet())
                .build();
    }

    public OrderDto convertToProduct(ProductOrder productOrder)
    {
        return OrderDto.builder()
                .orderId(productOrder.getId())
                .shipped_from_id(productOrder.getLocation().getId())
                .createdTime(productOrder.getCreatedAt())
                .deliveryAddress(new AddressDto(productOrder.getAddress_country(), productOrder.getAddress_city(), productOrder.getAddress_county(), productOrder.getAddress_street_address()))
                .build();
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

    public ProductOrder convertFromDTO(CreateOrderDto createOrderDto) {
        return ProductOrder.builder()
                .id(createOrderDto.getOrderId())
                .createdAt(createOrderDto.getCreatedTime())
                .address_country(createOrderDto.getDeliveryAddress().getAddressCountry())
                .address_city(createOrderDto.getDeliveryAddress().getAddressCity())
                .address_county(createOrderDto.getDeliveryAddress().getAddressCounty())
                .address_street_address(createOrderDto.getDeliveryAddress().getAddressStreet())
                .build();

    }

    public ProductOrderDetail toOrderDetail(OrderDetailDto orderDetailDto)
    {
        return ProductOrderDetail.builder()
                .productId(orderDetailDto.getProductId())
                .quantity(orderDetailDto.getQuantity())
                .build();
    }

    public OrderDto toOrderDto(ProductOrder productOrder) {
        return OrderDto.builder()
                .orderId(productOrder.getId())
                .customerID(productOrder.getCustomer().getId())
                .productOrders(productOrder.getOrderDetails().stream().map(this::toOrderDetailDtowithProductOrderDetail).toList())
                .deliveryAddress(new AddressDto(productOrder.getAddress_country(), productOrder.getAddress_city(), productOrder.getAddress_county(), productOrder.getAddress_street_address()))
                .createdTime(productOrder.getCreatedAt())
                .build();
    }
    public OrderDetailDto toOrderDetailDtowithProductOrderDetail(ProductOrderDetail productOrderDetail)
    {
        return OrderDetailDto.builder()
                .productId(productOrderDetail.getProductId())
                .quantity(productOrderDetail.getQuantity())
                .build();
    }
}
