package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Table(name="location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address_country;
    private String address_city;
    private String address_county;
    private String address_street_address;

    @OneToMany(mappedBy = "location")
    private List<ProductOrder> orders;
    @OneToOne(mappedBy = "location")
    private Revenue revenues;

    @OneToMany(mappedBy = "location")
    private Set<Stock> stocks;
}


