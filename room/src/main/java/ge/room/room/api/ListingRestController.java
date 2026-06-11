package ge.room.room.api;

import ge.room.room.model.Listing;
import ge.room.room.service.ListingService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/listings")
public class ListingRestController {

    private final ListingService listingService;

    public ListingRestController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public List<Listing> getAllListings() {
        return listingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable Long id) {
        return listingService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteListing(@PathVariable Long id) {
        if (listingService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        listingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
