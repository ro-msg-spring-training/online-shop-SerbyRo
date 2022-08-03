package ro.msg.learning.shop.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.mappers.ProductCategoryMapper;
import ro.msg.learning.shop.controller.mappers.ProductMapper;

import ro.msg.learning.shop.controller.mappers.SupplierMapper;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductCombinedDto;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    private final ProductCategoryMapper productCategoryMapper;

    private final SupplierMapper supplierMapper;

    public ProductController(ProductService productService, ProductMapper productMapper, ProductCategoryMapper productCategoryMapper, SupplierMapper supplierMapper){
        this.productService=productService;
        this.productMapper = productMapper;
        this.productCategoryMapper = productCategoryMapper;
        this.supplierMapper = supplierMapper;
    }
     @GetMapping("/products")
    public List<ProductCombinedDto> getAllProducts(){
        //return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts().stream().map(productMapper::toProductDto).collect(Collectors.toList()));
         return productService.getAllProducts().stream().map(productMapper::toProductDto).collect(Collectors.toList());
     }

     @GetMapping("/products/{productId}")
    public ResponseEntity<ProductCombinedDto> getProductById(@PathVariable Long productId)
     {
         return ResponseEntity.status(HttpStatus.OK).body(productMapper.toProductDto(productService.findProductById(productId)));
     }

     @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto createProductDto)
     {
         productService.addProduct(productMapper.toProductfromCreatedDto(createProductDto), createProductDto.getProductCategoryId(), createProductDto.getSupplierId());
         return new ResponseEntity<>("Product successfully created!",HttpStatus.CREATED);
     }

     @DeleteMapping("/products/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId)
     {
         productService.deleteProduct(productId);
         return new ResponseEntity<>("Product is deleted successfully",HttpStatus.OK);
     }
     @PutMapping("/products/{productId}")
     public ResponseEntity<Object> updateProduct(@RequestBody ProductDto createProductDto, @PathVariable Long productId)
     {
         productService.updateProduct(productMapper.toProductfromCreatedDto(createProductDto),
                 createProductDto.getProductCategoryId(), createProductDto.getSupplierId(),productId);
         return new ResponseEntity<>("Product is updated successfully!",HttpStatus.OK);
     }
}
