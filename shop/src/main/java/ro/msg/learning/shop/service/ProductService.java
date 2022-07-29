package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.savedtos.SaveProductCategoryDto;
import ro.msg.learning.shop.dto.savedtos.SaveProductDto;
import ro.msg.learning.shop.dto.savedtos.SaveSupplierDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.IProductCategoryInterfaceRepository;
import ro.msg.learning.shop.repository.IProductInterfaceRepository;
import ro.msg.learning.shop.repository.ISupplierInterfaceRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private IProductInterfaceRepository productInterfaceRepository;

    private ISupplierInterfaceRepository supplierInterfaceRepository;

    private IProductCategoryInterfaceRepository productCategoryInterfaceRepository;

    public ProductService(IProductInterfaceRepository productInterfaceRepository,ISupplierInterfaceRepository supplierInterfaceRepository,IProductCategoryInterfaceRepository productCategoryInterfaceRepository)
    {
        this.productInterfaceRepository=productInterfaceRepository;
        this.productCategoryInterfaceRepository= productCategoryInterfaceRepository;
        this.supplierInterfaceRepository=supplierInterfaceRepository;
    }

    public ProductCategory addProductCategory(SaveProductCategoryDto saveProductCategoryDto)
    {
        return productCategoryInterfaceRepository.save(saveProductCategoryDto.toProductCategory());
    }

    public Optional<ProductCategory> findProductCategoryById(Long categoryId){
        return productCategoryInterfaceRepository.findById(categoryId);
    }
    public List<ProductCategory> getAllProductCategories(){
        return productCategoryInterfaceRepository.findAll();
    }

    public void deleteProductCategoryById(Long categoryId){
        if (productCategoryInterfaceRepository.existsById(categoryId)){
            productCategoryInterfaceRepository.deleteById(categoryId);
        }
    }

    public Supplier addSupplier(SaveSupplierDto saveSupplierDto)
    {
        return supplierInterfaceRepository.save(saveSupplierDto.toSupplier());
    }

    public Optional<Supplier> findSupplierById(Long supplierId){
        return supplierInterfaceRepository.findById(supplierId);
    }
    public List<Supplier> getAllSuppliers(){
        return supplierInterfaceRepository.findAll();
    }
    public void deleteSupplierById(Long supplierId){
        if (supplierInterfaceRepository.existsById(supplierId)){
            supplierInterfaceRepository.deleteById(supplierId);
        }
    }

    @Transactional
    public Product addProduct(SaveProductDto saveProductDto){
        ProductCategory productCategory = productCategoryInterfaceRepository.getById(saveProductDto.getProductCategory());
        Supplier supplier = supplierInterfaceRepository.getById(saveProductDto.getSupplier());

        Product product = new Product( saveProductDto.getName(), saveProductDto.getDescription(), saveProductDto.getPrice(), saveProductDto.getWeight(), saveProductDto.getImageUrl(), productCategory,supplier);
        return productInterfaceRepository.save(product);
    }

    public Optional<Product> findProductById(Long productId){
        return productInterfaceRepository.findById(productId);
    }
    public List<Product> getAllProducts() {
        return productInterfaceRepository.findAll();
    }

    @Transactional
    public void deleteProductById(Long productId) {
        if (productInterfaceRepository.existsById(productId)) {
            productInterfaceRepository.deleteById(productId);
        }
    }

}
