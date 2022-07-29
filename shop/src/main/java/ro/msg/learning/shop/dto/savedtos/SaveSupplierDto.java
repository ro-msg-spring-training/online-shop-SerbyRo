package ro.msg.learning.shop.dto.savedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.model.Supplier;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
public class SaveSupplierDto {
    private String name;

    public Supplier toSupplier(){
        return new Supplier(null,name,new ArrayList<>());
    }
}
