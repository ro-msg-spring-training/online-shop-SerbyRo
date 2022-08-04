package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.repository.IProductCategoryInterfaceRepository;
import ro.msg.learning.shop.service.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class ProductCategoryService {
    @Autowired
    private IProductCategoryInterfaceRepository productCategoryInterfaceRepository;

    public ProductCategory findProductCategoryById(Long categoryId)
    {
        if (productCategoryInterfaceRepository.existsById(categoryId))
        {
            return productCategoryInterfaceRepository.getById(categoryId);
        }
        else{
            throw new NotFoundException("Category doesn't exist");
        }
    }
}
