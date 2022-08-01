package ro.msg.learning.shop.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.dto.ProductCombinedDto;
import ro.msg.learning.shop.dto.SupplierDto;

import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.exceptions.ProductException;
import ro.msg.learning.shop.utils.Status;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }
     @GetMapping("/suppliers")
    public List<SupplierDto> getAllSupliers(){
        return productService.getAllSuppliers();
     }

     @GetMapping("/suppliers/{suplierId}")
    public ResponseEntity<SupplierDto> findSupplieById(@PathVariable Long suplierId)
     {
         try{
             SupplierDto supplierDto = productService.findSupplierById(suplierId);
             return ResponseEntity.status(HttpStatus.OK).body(supplierDto);
         }catch (ProductException ex)
         {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
         }
     }
     @PostMapping("/suppliers")
    public SupplierDto addSupplier(@RequestBody SupplierDto supplierDto, HttpServletResponse response)
     {
             return productService.addSupplier(supplierDto);
     }

     @PutMapping("/suppliers/{supplierId}")
    public void updateSupplier(@PathVariable Long supplierId,@RequestBody SupplierDto supplierDto) throws ProductException {
         productService.updateSupplier(supplierId,supplierDto);
     }
     @DeleteMapping("/suppliers/{supplierId}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long supplierId)
     {
         try{
             productService.deleteSupplierById(supplierId);
             return ResponseEntity.status(HttpStatus.OK).body("Supplier deleted successfully!");
         }catch (ProductException ex)
         {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
         }
     }

    @GetMapping("/product_categories")
    public List<ProductCategoryDto> getAllProductCategories(){
        return productService.getAllProductCategories();
    }

    @GetMapping("/product_categories/{categoryId}")
    public ResponseEntity<ProductCategoryDto> findProductCategoryById(@PathVariable Long categoryId)
    {
        try{
            ProductCategoryDto productCategoryDto = productService.findProductCategoryById(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(productCategoryDto);
        }catch (ProductException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/product_categories")
    public ProductCategoryDto addProductCategory(@RequestBody ProductCategoryDto productCategoryDto, HttpServletResponse response)
    {
        return productService.addProductCategory(productCategoryDto);
    }

    @PutMapping("/product_categories/{categoryId}")
    public void updateSupplier(@PathVariable Long categoryId,@RequestBody ProductCategoryDto productCategoryDto) throws ProductException {
        productService.updateProductCategory(categoryId,productCategoryDto);
    }

    @DeleteMapping("/product_categories/{categoryId}")
    public ResponseEntity<String> deleteProductCategory(@PathVariable Long categoryId)
    {
        try{
            productService.deleteProductCategoryById(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body("Product category deleted successfully!");
        }catch (ProductException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/products")
    public List<ProductCombinedDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductCombinedDto> findProductById(@PathVariable Long productId)
    {
        try{
            ProductCombinedDto productCombinedDto = productService.findProductById(productId);
            return ResponseEntity.status(HttpStatus.OK).body(productCombinedDto);
        }catch (ProductException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/products")
    public ProductCombinedDto addProduct(@RequestBody ProductCombinedDto productCombinedDto, HttpServletResponse response) throws ProductException {
        return productService.addProduct(productCombinedDto);
    }

    @PutMapping("/products/{productId}")
    public void updateProduct(@PathVariable Long productId,@RequestBody ProductCombinedDto productCombinedDto) throws ProductException {
        productService.updateProduct(productId,productCombinedDto);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId)
    {
        try{
            productService.deleteProductById(productId);
            return ResponseEntity.status(HttpStatus.OK).body("Product category deleted successfully!");
        }catch (ProductException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
