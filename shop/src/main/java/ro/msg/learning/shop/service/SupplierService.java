package ro.msg.learning.shop.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ISupplierInterfaceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService {
    @Autowired
    private ISupplierInterfaceRepository supplierInterfaceRepository;

    public Supplier findSupplierById(Long supplierId)
    {
        return supplierInterfaceRepository.getById(supplierId);
    }
}
