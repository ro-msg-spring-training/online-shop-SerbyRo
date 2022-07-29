package ro.msg.learning.shop.service;


import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ISupplierInterfaceRepository;

@Service
public class SupplierService {

    private ISupplierInterfaceRepository supplierInterfaceRepository;

    public SupplierService(ISupplierInterfaceRepository supplierInterfaceRepository)
    {
        this.supplierInterfaceRepository = supplierInterfaceRepository;
    }

    public Supplier findSupplierByName(String name) throws ShopException {
        return supplierInterfaceRepository.findSupplierByName(name)
                .orElseThrow(() -> new ShopException("There is no supplier with this name: " + name));
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierInterfaceRepository.save(supplier);
    }

    public void deleteAllSuppliers(){
        supplierInterfaceRepository.deleteAll();
    }
}
