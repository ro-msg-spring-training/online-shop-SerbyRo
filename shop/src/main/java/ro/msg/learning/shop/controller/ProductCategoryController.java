package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.service.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductCategoryController {
    private final ProductCategoryService productService;

    public ProductCategoryController(ProductCategoryService productService) {
        this.productService = productService;
    }

    @PostMapping("/categories")
    public void saveProductCategory(@RequestBody ProductCategory productCategory) {
        this.productService.saveProductCategory(productCategory);
    }

    @GetMapping("/categories")
    public List<ProductCategory> findAllProductCategories() {
        return this.productService.getAllProductCategories();
    }
}


