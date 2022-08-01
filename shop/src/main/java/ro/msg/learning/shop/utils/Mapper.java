package ro.msg.learning.shop.utils;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.dto.ProductCombinedDto;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

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
