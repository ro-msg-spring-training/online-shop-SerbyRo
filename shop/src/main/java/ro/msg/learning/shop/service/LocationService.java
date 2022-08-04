package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.repository.ILocationInterfaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private ILocationInterfaceRepository locationInterfaceRepository;



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
