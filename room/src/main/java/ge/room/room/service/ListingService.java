package ge.room.room.service;

import ge.room.room.model.Listing;
import ge.room.room.repository.ListingRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ListingService {

    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @CacheEvict(cacheNames = "listings", allEntries = true)
    public Listing save(Listing listing) {
        return listingRepository.save(listing);
    }

    @Cacheable(cacheNames = "listings", key = "'all'")
    public List<Listing> findAll() {
        return listingRepository.findAll();
    }

    @Cacheable(cacheNames = "listings", key = "#id")
    public Optional<Listing> findById(Long id) {
        return listingRepository.findById(id);
    }

    @CacheEvict(cacheNames = "listings", allEntries = true)
    public void deleteById(Long id) {
        listingRepository.deleteById(id);
    }
}
