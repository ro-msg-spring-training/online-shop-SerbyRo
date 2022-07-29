package ro.msg.learning.shop.controller;


import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductCombinedDto;
import ro.msg.learning.shop.dto.savedtos.SaveProductDto;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping
    public ProductCombinedDto addProduct(@RequestBody SaveProductDto saveProductDto)
    {
       return new ProductCombinedDto(productService.addProduct(saveProductDto));
    }

    @GetMapping
    public List<ProductCombinedDto> getAllProductsCombined(){
        return productService.getAllProducts().stream().map(ProductCombinedDto::new).toList();
    }

    @GetMapping("/{productId}")
    public ProductCombinedDto findProductCombinedById(@PathVariable Long productId){
        return productService.findProductById(productId).map(ProductCombinedDto::new).orElseThrow();
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable Long productId){
        productService.deleteProductById(productId);
    }

}
