package ge.room.room.service;

import ge.room.room.model.Listing;
import ge.room.room.repository.ListingRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ListingService {

    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public Listing save(Listing listing) {
        return listingRepository.save(listing);
    }

    public List<Listing> findAll() {
        return listingRepository.findAll();
    }

    public Optional<Listing> findById(Long id) {
        return listingRepository.findById(id);
    }

    public void deleteById(Long id) {
        listingRepository.deleteById(id);
    }
}
