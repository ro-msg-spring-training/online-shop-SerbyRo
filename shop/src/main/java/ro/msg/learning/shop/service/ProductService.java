package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.dto.ProductCombinedDto;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.IProductCategoryInterfaceRepository;
import ro.msg.learning.shop.repository.IProductInterfaceRepository;
import ro.msg.learning.shop.repository.ISupplierInterfaceRepository;
import ro.msg.learning.shop.service.exceptions.NotFoundException;
import ro.msg.learning.shop.service.exceptions.ProductException;
import ro.msg.learning.shop.utils.Mapper;
import ro.msg.learning.shop.utils.Status;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private IProductInterfaceRepository productInterfaceRepository;
    @Autowired
    private ISupplierInterfaceRepository supplierInterfaceRepository;
    @Autowired
    private IProductCategoryInterfaceRepository productCategoryInterfaceRepository;




    private Mapper mapper = new Mapper();

    public ProductService(IProductInterfaceRepository productInterfaceRepository,ISupplierInterfaceRepository supplierInterfaceRepository,IProductCategoryInterfaceRepository productCategoryInterfaceRepository)
    {
        this.productInterfaceRepository=productInterfaceRepository;
        this.productCategoryInterfaceRepository= productCategoryInterfaceRepository;
        this.supplierInterfaceRepository=supplierInterfaceRepository;
    }


    public ProductCategory saveProductCategory(ProductCategory productCategory)
    {
        return productCategoryInterfaceRepository.save(productCategory);
    }

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

    public List<ProductCategory> getAllProductsCategories()
    {
        return productCategoryInterfaceRepository.findAll();
    }

    public void deleteProductCategoryById(Long categoryId)
    {
        if (productCategoryInterfaceRepository.existsById(categoryId))
        {
            productCategoryInterfaceRepository.deleteById(categoryId);
        }
    }


    public Supplier saveSupplier(Supplier supplier)
    {
        return supplierInterfaceRepository.save(supplier);
    }

    public Supplier findSupplierById(Long supplierId)
    {
        return supplierInterfaceRepository.getById(supplierId);
    }

    public List<Supplier> getAllSuppliers()
    {
        return supplierInterfaceRepository.findAll();
    }

    public void deleteSupplierById(Long supplierId)
    {
        if (supplierInterfaceRepository.existsById(supplierId))
        {
            supplierInterfaceRepository.deleteById(supplierId);
        }
    }


        public List<Product> getAllProducts()
        {
            return productInterfaceRepository.findAll();
        }
        public Product findProductById(Long productId)
        {
            return productInterfaceRepository.getById(productId);
        }

        public void addProduct(Product product,long productCategoryId, long supplierId)
        {
            saveProduct(product,productCategoryId,supplierId);
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

        public void updateProduct(Product product,Long productCategoryId,Long supplierId,Long productId)
        {
            System.out.println(productId);
            System.out.println(productCategoryId);
            if (productInterfaceRepository.existsById(productId)){
                product.setId(productId);
                saveProduct(product,productCategoryId,supplierId);
            }else
            {
                throw new NotFoundException("Product not found!");
            }
        }
        public void saveProduct(Product product, long productCategoryId, long supplierId) {
            if (productCategoryInterfaceRepository.existsById(productCategoryId)&&
                    supplierInterfaceRepository.existsById(supplierId))
            {
                ProductCategory productCategory = this.findProductCategoryById(productCategoryId);
                Supplier supplier = this.findSupplierById(supplierId);
                product.setProductCategory(productCategory);
                product.setSupplier(supplier);
                productInterfaceRepository.save(product);
            }else
            {
                throw new NotFoundException("Product category or supplier not found!");
            }
        }

}
