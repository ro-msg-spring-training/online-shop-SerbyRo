package ro.msg.learning.shop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.model.Supplier;

@Data
@Builder
@AllArgsConstructor
public class SupplierDto {
    private Long supplierId;
    private String name;

//    public SupplierDto(Supplier supplier)
//    {
//        this.supplierId = supplier.getId();
//        this.name = supplier.getName();
//    }
}
