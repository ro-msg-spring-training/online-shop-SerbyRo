package ro.msg.learning.shop.controller.mappers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.AddressDto;
import ro.msg.learning.shop.model.Address;

@Component
public class AddressMapper {
    public Address toAddressEntity(AddressDto addressDto)
    {
        return Address.builder()
                .country(addressDto.getAddressCountry())
                .city(addressDto.getAddressCity())
                .county(addressDto.getAddressCounty())
                .street(addressDto.getAddressStreet())
                .build();
    }
}
