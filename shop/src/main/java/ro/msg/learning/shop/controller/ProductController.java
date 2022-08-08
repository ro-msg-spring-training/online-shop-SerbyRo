package ro.msg.learning.shop.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.mappers.ProductCategoryMapper;
import ro.msg.learning.shop.controller.mappers.ProductMapper;

import ro.msg.learning.shop.controller.mappers.SupplierMapper;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductCombinedDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    private final ProductCategoryMapper productCategoryMapper;

    private final SupplierMapper supplierMapper;

     @GetMapping("/products")
    public List<ProductCombinedDto> getAllProducts(){
         return productService.getAllProducts().stream().map(productMapper::toProductCombinedDto).collect(Collectors.toList());

     }

     @GetMapping("/products/{productId}")
    public ResponseEntity<ProductCombinedDto> getProductById(@PathVariable Long productId)
     {
         return ResponseEntity.status(HttpStatus.OK).body(productMapper.toProductCombinedDto(productService.findProductById(productId)));
     }

     @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto createProductDto)
     {

         Product productCreated = productService.addProduct(productMapper.toProductfromCreatedDto(createProductDto), createProductDto.getProductCategoryId(), createProductDto.getSupplierId());
          return productMapper.toProductDto(productCreated);
     }

     @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable Long productId)
     {
         productService.deleteProduct(productId);
     }
     @PutMapping("/products/{productId}")
     public ProductDto updateProduct(@RequestBody ProductDto createProductDto, @PathVariable Long productId)
     {
         createProductDto.setId(productId);
         Product productUpdated = productService.updateProduct(productMapper.toProductfromCreatedDto(createProductDto),
                 createProductDto.getProductCategoryId(), createProductDto.getSupplierId());
         return productMapper.toProductDto(productUpdated);
     }
}
