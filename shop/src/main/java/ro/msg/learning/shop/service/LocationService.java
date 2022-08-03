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

//    public LocationDto addLocation(LocationDto locationDto)
//    {
//        Location location = mapper.toLocation(locationDto);
//        return mapper.toLocationDto(locationInterfaceRepository.save(location));
//    }
//
//    public LocationDto findLocationById(Long locationId) throws ProductException {
//        Optional<LocationDto> locationDto = locationInterfaceRepository.findById(locationId).map(mapper::toLocationDto);
//        if (locationDto.isPresent())
//        {
//            return locationDto.get();
//        }
//        else
//        {
//            throw new ProductException("The location doesn't exist!");
//        }
//    }
//    public List<LocationDto> getAllLocations(){
//        return locationInterfaceRepository.findAll()
//                .stream()
//                .map(mapper::toLocationDto)
//                .collect(Collectors.toList());
//    }
//
//    public void deleteLocationById(Long locationId) throws ProductException {
//        if (locationInterfaceRepository.existsById(locationId)){
//            locationInterfaceRepository.deleteById(locationId);
//        }
//        else
//        {
//            throw new ProductException("The Location doean't exist!");
//        }
//    }
//
//    public void updateLocation(Long locationId,LocationDto locationDto) throws ProductException{
//        locationInterfaceRepository.findById(locationId)
//                .orElseThrow(() ->new ProductException("Invalid locationId"));
//
//        Location location = mapper.toLocation(locationDto);
//        location.setId(locationId);
//        locationInterfaceRepository.save(location);
//    }
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
