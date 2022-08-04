package ro.msg.learning.shop.controller.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductCombinedDto;
import ro.msg.learning.shop.model.Product;

@Component
@AllArgsConstructor
public class ProductMapper {
    private ProductCategoryMapper productCategoryMapper;
    private SupplierMapper supplierMapper;



    public Product toProductEntity(ProductCombinedDto productCombinedDto)
    {
        return Product.builder()
                .id(productCombinedDto.getProductId())
                .name(productCombinedDto.getName())
                .description(productCombinedDto.getDescription())
                .price(productCombinedDto.getPrice())
                .weight(productCombinedDto.getWeight())
                .imageUrl(productCombinedDto.getImageUrl())
                .productCategory(productCategoryMapper.toProductCategoryEntity(productCombinedDto.getCategory()))
                .supplier(supplierMapper.toSupplierEntity(productCombinedDto.getSupplier()))
                .build();
    }

    public ProductCombinedDto toProductCombinedDto(Product product)
    {
        return ProductCombinedDto.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .weight(product.getWeight())
                .imageUrl(product.getImageUrl())
                .category(productCategoryMapper.toProductCategoryDto(product.getProductCategory()))
                .supplier(supplierMapper.toSupplierDto(product.getSupplier()))
                .build();
    }

    public ProductDto toProductDto(Product product)
    {
        return ProductDto.builder().
                id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .weight(product.getWeight())
                .imageUrl(product.getImageUrl())
                .productCategoryId(product.getProductCategory().getId())
                .supplierId(product.getSupplier().getId())
                .build();
    }

    public Product toProductfromCreatedDto(ProductDto createProductDto)
    {
        return Product.builder()
                .id(createProductDto.getId())
                .name(createProductDto.getName())
                .description(createProductDto.getDescription())
                .price(createProductDto.getPrice())
                .weight(createProductDto.getWeight())
                .imageUrl(createProductDto.getImageUrl())
                .build();
    }
}
