package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Table(name="product_category")
public class ProductCategory{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

   @OneToMany(mappedBy = "productCategory")
   @JsonManagedReference
   private Set<Product> products;

   public ProductCategory(String name, String description)
   {
       this.name = name;
       this.description = description;
   }

}
