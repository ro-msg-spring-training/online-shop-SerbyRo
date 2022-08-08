package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Table(name="location")
@EqualsAndHashCode(exclude = "stocks")
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

    public Location(String name, String address_country, String address_city, String address_county, String address_street_address) {
        this.name = name;
        this.address_country = address_country;
        this.address_city = address_city;
        this.address_county = address_county;
        this.address_street_address=address_street_address;
    }
}


