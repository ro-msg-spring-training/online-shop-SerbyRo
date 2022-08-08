package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.IProductCategoryInterfaceRepository;
import ro.msg.learning.shop.repository.IProductInterfaceRepository;
import ro.msg.learning.shop.repository.ISupplierInterfaceRepository;
import ro.msg.learning.shop.service.exceptions.NotFoundException;


import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    @Autowired
    private IProductInterfaceRepository productInterfaceRepository;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ProductCategoryService productCategoryService;
        public List<Product> getAllProducts()
        {
            return productInterfaceRepository.findAll();
        }
        public Product findProductById(Long productId)
        {
            return productInterfaceRepository.getById(productId);
        }

        public Product addProduct(Product product,long productCategoryId, long supplierId)
        {
            return saveProduct(product,productCategoryId,supplierId);
        }

        public void deleteProduct(Long productId){
            if (productInterfaceRepository.existsById(productId))
            {
                productInterfaceRepository.deleteById(productId);
            }
            else
            {
                throw new NotFoundException("Product not found!");
            }
        }

        public Product updateProduct(Product product,Long productCategoryId,Long supplierId)
        {

            if (productInterfaceRepository.existsById(product.getId())){
                product.setId(product.getId());
                return saveProduct(product,productCategoryId,supplierId);
            }else
            {
                throw new NotFoundException("Product not found!");
            }
        }
        public Product saveProduct(Product product, long productCategoryId, long supplierId) {
            if (productCategoryService.findProductCategoryById(productCategoryId) !=null &&
                    supplierService.findSupplierById(supplierId)!=null)
            {
                ProductCategory productCategory = productCategoryService.findProductCategoryById(productCategoryId);
                Supplier supplier = supplierService.findSupplierById(supplierId);
                product.setProductCategory(productCategory);
                product.setSupplier(supplier);
                return productInterfaceRepository.save(product);
            }else
            {
                throw new NotFoundException("Product category or supplier not found!");
            }
        }

}
