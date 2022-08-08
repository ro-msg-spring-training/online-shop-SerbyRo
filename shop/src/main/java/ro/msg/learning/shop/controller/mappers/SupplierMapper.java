package ro.msg.learning.shop.controller.mappers;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.model.Supplier;

@Component
public class SupplierMapper {
    public Supplier toSupplierEntity(SupplierDto supplierDto)
    {
        return Supplier.builder()
                .id(supplierDto.getSupplierId())
                .name(supplierDto.getName())
                .build();
    }

    public SupplierDto toSupplierDto(Supplier supplier)
    {
        return SupplierDto.builder()
                .supplierId(supplier.getId())
                .name(supplier.getName())
                .build();
    }
}
