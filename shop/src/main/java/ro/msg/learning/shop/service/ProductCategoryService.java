package ro.msg.learning.shop.service;


import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.repository.IProductCategoryInterfaceRepository;

import java.util.List;

@Service
public class ProductCategoryService {

    private IProductCategoryInterfaceRepository productCategoryInterfaceRepository;

    public ProductCategoryService(IProductCategoryInterfaceRepository iProductCategoryInterfaceRepository)
    {
        this.productCategoryInterfaceRepository=iProductCategoryInterfaceRepository;
    }

    public void saveProductCategory(ProductCategory productCategory){
        productCategoryInterfaceRepository.save(productCategory);
    }

    public List<ProductCategory> getAllProductCategories()
    {
        return productCategoryInterfaceRepository.findAll();
    }
}
