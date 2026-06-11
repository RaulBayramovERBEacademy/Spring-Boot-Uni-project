package ge.room.room.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ge.room.room.model.Listing;
import ge.room.room.repository.ListingRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListingServiceTest {

    @Mock
    private ListingRepository listingRepository;

    @InjectMocks
    private ListingService listingService;

    @Test
    void save_delegatesToRepository() {
        Listing listing = sampleListing();
        when(listingRepository.save(listing)).thenReturn(listing);

        Listing saved = listingService.save(listing);

        assertThat(saved).isSameAs(listing);
        verify(listingRepository).save(listing);
    }

    @Test
    void findAll_returnsRepositoryResults() {
        List<Listing> listings = List.of(sampleListing());
        when(listingRepository.findAll()).thenReturn(listings);

        List<Listing> result = listingService.findAll();

        assertThat(result).containsExactlyElementsOf(listings);
        verify(listingRepository).findAll();
    }

    @Test
    void findById_returnsOptionalFromRepository() {
        Listing listing = sampleListing();
        when(listingRepository.findById(1L)).thenReturn(Optional.of(listing));

        Optional<Listing> result = listingService.findById(1L);

        assertThat(result).contains(listing);
        verify(listingRepository).findById(1L);
    }

    private static Listing sampleListing() {
        Listing listing = new Listing();
        listing.setId(1L);
        listing.setTitle("Cozy Studio");
        listing.setDescription("Bright room near the center");
        listing.setType("studio");
        listing.setCity("Tbilisi");
        listing.setPrice(450.0);
        return listing;
    }
}
