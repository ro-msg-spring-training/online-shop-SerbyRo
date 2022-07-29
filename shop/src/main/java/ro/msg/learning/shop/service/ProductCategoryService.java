package ro.msg.learning.shop.service;


import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Location;
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

    public ProductCategory findOneProductCategoryById(Long productCategoryId) throws ShopException {
        return productCategoryInterfaceRepository.findById(productCategoryId).orElseThrow(()-> new ShopException("Categoria produsului cu id-ul "+ productCategoryId + " nu exista!"));
    }

    public void deleteProductCategoryById(Long productCategoryId)
    {
        if (productCategoryInterfaceRepository.existsById(productCategoryId))
        {
            productCategoryInterfaceRepository.deleteById(productCategoryId);
        }
    }

    public ProductCategory updateProductCategory(ProductCategory productCategory) throws ShopException {
        if (productCategoryInterfaceRepository.findProductCategoryByName(productCategory.getName()).isPresent() &&
                !productCategoryInterfaceRepository.findProductCategoryByName(productCategory.getName()).get().getId().equals(productCategory.getId())){
            throw new ShopException("Exista o locatie cu acelasi nume!");
        }
        else
        {
            return productCategoryInterfaceRepository.save(productCategory);
        }
    }
}
