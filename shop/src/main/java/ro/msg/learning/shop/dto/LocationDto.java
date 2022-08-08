package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDto {
    private Long locationId;
    private String name;
    private String addressCountry;
    private String addressCounty;
    private String addressCity;
    private String addressStreet;
}
