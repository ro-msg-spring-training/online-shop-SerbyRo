package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.repository.ILocationInterfaceRepository;

import java.util.Collection;

@Service
public class LocationService {
    private ILocationInterfaceRepository locationInterfaceRepository;

    public LocationService(ILocationInterfaceRepository locationInterfaceRepository)
    {
        this.locationInterfaceRepository = locationInterfaceRepository;
    }

    public void addLocation(Location location)
    {
        locationInterfaceRepository.save(location);
    }
    public Collection<Location> getAllLocations(){
        return locationInterfaceRepository.findAll();
    }

    public Location findOneLocationById(Long locationId) throws ShopException {
        return locationInterfaceRepository.findById(locationId).orElseThrow(()-> new ShopException("Locatia cu id-ul "+ locationId + " nu exista!"));
    }

    public void deleteLocationById(Long locationId)
    {
        if (locationInterfaceRepository.existsById(locationId))
        {
            locationInterfaceRepository.deleteById(locationId);
        }
    }

    public Location updateLocation(Location location) throws ShopException {
        if (locationInterfaceRepository.findLocationByName(location.getName()).isPresent() &&
                !locationInterfaceRepository.findLocationByName(location.getName()).get().getId().equals(location.getId())){
            throw new ShopException("Exista o locatie cu acelasi nume!");
        }
        else
        {
            return locationInterfaceRepository.save(location);
        }
    }
}
