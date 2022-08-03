package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.repository.ILocationInterfaceRepository;
import ro.msg.learning.shop.service.exceptions.ProductException;
import ro.msg.learning.shop.utils.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {
    @Autowired
    private ILocationInterfaceRepository locationInterfaceRepository;

    private Mapper mapper = new Mapper();

    public LocationService(ILocationInterfaceRepository locationInterfaceRepository)
    {
        this.locationInterfaceRepository = locationInterfaceRepository;
    }


    public Location saveLocation(Location location)
    {
        return locationInterfaceRepository.save(location);
    }
    public Optional<Location> findLocationById(Long locationId){
        return locationInterfaceRepository.findById(locationId);
    }

    public List<Location> getAllLocations(){
        return locationInterfaceRepository.findAll();
    }

    public void deleteLocationById(Long locationId)
    {
        if(locationInterfaceRepository.existsById(locationId))
        {
            locationInterfaceRepository.deleteById(locationId);
        }
    }

}
