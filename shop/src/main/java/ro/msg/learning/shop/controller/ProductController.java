package ro.msg.learning.shop.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.mappers.ProductCategoryMapper;
import ro.msg.learning.shop.controller.mappers.ProductMapper;

import ro.msg.learning.shop.controller.mappers.SupplierMapper;
import ro.msg.learning.shop.dto.CreateProductDto;
import ro.msg.learning.shop.dto.ProductCombinedDto;
import ro.msg.learning.shop.model.Product;
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
//     @GetMapping("/suppliers")
//    public List<SupplierDto> getAllSupliers(){
//        return productService.getAllSuppliers();
//     }
//
//     @GetMapping("/suppliers/{suplierId}")
//    public ResponseEntity<SupplierDto> findSupplieById(@PathVariable Long suplierId)
//     {
//         try{
//             SupplierDto supplierDto = productService.findSupplierById(suplierId);
//             return ResponseEntity.status(HttpStatus.OK).body(supplierDto);
//         }catch (ProductException ex)
//         {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//         }
//     }
//     @PostMapping("/suppliers")
//    public SupplierDto addSupplier(@RequestBody SupplierDto supplierDto, HttpServletResponse response)
//     {
//             return productService.addSupplier(supplierDto);
//     }
//
//     @PutMapping("/suppliers/{supplierId}")
//    public void updateSupplier(@PathVariable Long supplierId,@RequestBody SupplierDto supplierDto) throws ProductException {
//         productService.updateSupplier(supplierId,supplierDto);
//     }
//     @DeleteMapping("/suppliers/{supplierId}")
//    public ResponseEntity<String> deleteSupplier(@PathVariable Long supplierId)
//     {
//         try{
//             productService.deleteSupplierById(supplierId);
//             return ResponseEntity.status(HttpStatus.OK).body("Supplier deleted successfully!");
//         }catch (ProductException ex)
//         {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//         }
//     }

//    @GetMapping("/product_categories")
//    public List<ProductCategoryDto> getAllProductCategories(){
//        return productService.getAllProductCategories();
//    }

//    @GetMapping("/product_categories/{categoryId}")
//    public ResponseEntity<ProductCategoryDto> findProductCategoryById(@PathVariable Long categoryId)
//    {
//        try{
//            ProductCategoryDto productCategoryDto = productService.findProductCategoryById(categoryId);
//            return ResponseEntity.status(HttpStatus.OK).body(productCategoryDto);
//        }catch (ProductException ex)
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
//
//    @PostMapping("/product_categories")
//    public ProductCategoryDto addProductCategory(@RequestBody ProductCategoryDto productCategoryDto, HttpServletResponse response)
//    {
//        return productService.addProductCategory(productCategoryDto);
//    }
//
//    @PutMapping("/product_categories/{categoryId}")
//    public void updateSupplier(@PathVariable Long categoryId,@RequestBody ProductCategoryDto productCategoryDto) throws ProductException {
//        productService.updateProductCategory(categoryId,productCategoryDto);
//    }
//
//    @DeleteMapping("/product_categories/{categoryId}")
//    public ResponseEntity<String> deleteProductCategory(@PathVariable Long categoryId)
//    {
//        try{
//            productService.deleteProductCategoryById(categoryId);
//            return ResponseEntity.status(HttpStatus.OK).body("Product category deleted successfully!");
//        }catch (ProductException ex)
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        }
//    }

//    @GetMapping("/products")
//    public List<ProductCombinedDto> getAllProducts(){
//        return productService.getAllProducts();
//    }
//
//    @GetMapping("/products/{productId}")
//    public ResponseEntity<ProductCombinedDto> findProductById(@PathVariable Long productId)
//    {
//        try{
//            ProductCombinedDto productCombinedDto = productService.findProductById(productId);
//            return ResponseEntity.status(HttpStatus.OK).body(productCombinedDto);
//        }catch (ProductException ex)
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
//
//    @PostMapping("/products")
//    public ProductCombinedDto addProduct(@RequestBody ProductCombinedDto productCombinedDto, HttpServletResponse response) throws ProductException {
//        return productService.addProduct(productCombinedDto);
//    }
//
//    @PutMapping("/products/{productId}")
//    public void updateProduct(@PathVariable Long productId,@RequestBody ProductCombinedDto productCombinedDto) throws ProductException {
//        productService.updateProduct(productId,productCombinedDto);
//    }
//
//    @DeleteMapping("/products/{productId}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Long productId)
//    {
//        try{
//            productService.deleteProductById(productId);
//            return ResponseEntity.status(HttpStatus.OK).body("Product category deleted successfully!");
//        }catch (ProductException ex)
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        }
//    }
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
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductDto createProductDto)
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
     public ResponseEntity<Object> updateProduct(@RequestBody CreateProductDto createProductDto,@PathVariable Long productId)
     {
         productService.updateProduct(productMapper.toProductfromCreatedDto(createProductDto),
                 createProductDto.getProductCategoryId(), createProductDto.getSupplierId(),productId);
         return new ResponseEntity<>("Product is updated successfully!",HttpStatus.OK);
     }
}
