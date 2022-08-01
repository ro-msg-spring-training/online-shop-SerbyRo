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

    public ProductCategoryDto addProductCategory(ProductCategoryDto productCategoryDto)
    {
        ProductCategory productCategory = mapper.toProductCategory(productCategoryDto);
        return mapper.toProductCategoryDto(productCategoryInterfaceRepository.save(productCategory));
    }

    public ProductCategoryDto findProductCategoryById(Long categoryId) throws ProductException {
        Optional<ProductCategoryDto> productCategoryDto = productCategoryInterfaceRepository.findById(categoryId).map(mapper::toProductCategoryDto);
        if (productCategoryDto.isPresent())
        {
            return productCategoryDto.get();
        }
        else
        {
            throw new ProductException("The Product Category doesn't exist!");
        }
    }
    public List<ProductCategoryDto> getAllProductCategories(){
        return productCategoryInterfaceRepository.findAll()
                .stream()
                .map(mapper::toProductCategoryDto)
                .collect(Collectors.toList());
    }

    public void deleteProductCategoryById(Long categoryId) throws ProductException {
        if (productCategoryInterfaceRepository.existsById(categoryId)){
            productCategoryInterfaceRepository.deleteById(categoryId);
        }
        else
        {
            throw new ProductException("The Product doean't exist!");
        }
    }

    public void updateProductCategory(Long categoryId,ProductCategoryDto productCategoryDto) throws ProductException{
        productCategoryInterfaceRepository.findById(categoryId)
                .orElseThrow(() ->new ProductException("Invalid categoryId"));

        ProductCategory productCategory = mapper.toProductCategory(productCategoryDto);
        productCategory.setId(categoryId);
        productCategoryInterfaceRepository.save(productCategory);
    }

    public SupplierDto addSupplier(SupplierDto supplierDto)
    {
        Supplier supplier = mapper.toSupplier(supplierDto);
        return mapper.toSupplierDto(supplierInterfaceRepository.save(supplier));
    }

    public SupplierDto findSupplierById(Long supplierId) throws ProductException {
        Optional<SupplierDto> supplierDto = supplierInterfaceRepository.findById(supplierId).map(mapper::toSupplierDto);
        if (supplierDto.isPresent())
        {
            return  supplierDto.get();
        }
        else
        {
            throw new ProductException("The Product doesn't exist!");
        }
    }
    public List<SupplierDto> getAllSuppliers(){
        return supplierInterfaceRepository.findAll()
                .stream()
                .map(mapper::toSupplierDto)
                .collect(Collectors.toList());
    }
    public void deleteSupplierById(Long supplierId) throws ProductException {
        if (supplierInterfaceRepository.existsById(supplierId)){
            supplierInterfaceRepository.deleteById(supplierId);
        }
        else
        {
            throw new ProductException("The product doesn't exist!");
        }
    }

    public void updateSupplier(Long supplierId, SupplierDto supplierDto) throws ProductException {
        supplierInterfaceRepository.findById(supplierId)
                .orElseThrow(() ->new ProductException("Invalid supplierId"));

        Supplier supplier = mapper.toSupplier(supplierDto);
        supplier.setId(supplierId);
        supplierInterfaceRepository.save(supplier);
    }

    public ProductCombinedDto addProduct(ProductCombinedDto productCombinedDto) throws ProductException {
        if (!productCategoryInterfaceRepository.existsById(productCombinedDto.getCategory().getCategoryId())){
            throw new ProductException("The category of this product doesn't exist!");
        }
        if(!supplierInterfaceRepository.existsById(productCombinedDto.getSupplier().getSupplierId())){
            throw new ProductException("The supplier for thisproduct doesn't exist!");
        }
        Product product = mapper.toProduct(productCombinedDto);
        return mapper.toProductDto(productInterfaceRepository.save(product));
    }

    public ProductCombinedDto findProductById(Long productId) throws ProductException {
        Optional<ProductCombinedDto> productCombinedDto = productInterfaceRepository.findById(productId).map(mapper::toProductDto);
        if (productCombinedDto.isPresent())
        {
            return productCombinedDto.get();
        }
        else
        {
            throw new ProductException("The Product doesn't exist!");
        }
    }
    public List<ProductCombinedDto> getAllProducts() {
        return productInterfaceRepository.findAll().
                stream()
                .map(mapper::toProductDto)
                .collect(Collectors.toList());
    }

    public void deleteProductById(Long productId) throws ProductException {
        if (productInterfaceRepository.existsById(productId)) {
            productInterfaceRepository.deleteById(productId);
        }
        else
        {
            throw new ProductException("The Product doesn't exist!");
        }
    }

    public void updateProduct(Long productId,ProductCombinedDto productCombinedDto) throws ProductException {
        productCategoryInterfaceRepository.findById(productCombinedDto.getCategory().getCategoryId())
                .orElseThrow(() ->new ProductException("Invalid categoryId"));

        supplierInterfaceRepository.findById(productCombinedDto.getSupplier().getSupplierId())
                .orElseThrow(() ->new ProductException("Invalid supplierId"));

        productInterfaceRepository.findById(productId)
                .orElseThrow(() ->new ProductException("Invalid productId"));

        Product product = mapper.toProduct(productCombinedDto);
        product.setId(productId);
        productInterfaceRepository.save(product);
    }

}
