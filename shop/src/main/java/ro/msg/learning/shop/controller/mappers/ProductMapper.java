package ro.msg.learning.shop.controller.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductCombinedDto;
import ro.msg.learning.shop.model.Product;

@Component
public class ProductMapper {
    private ProductCategoryMapper productCategoryMapper;
    private SupplierMapper supplierMapper;

    public ProductMapper(ProductCategoryMapper productCategoryMapper,SupplierMapper supplierMapper)
    {
        this.productCategoryMapper=productCategoryMapper;
        this.supplierMapper = supplierMapper;
    }

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

    public ProductCombinedDto toProductDto(Product product)
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
