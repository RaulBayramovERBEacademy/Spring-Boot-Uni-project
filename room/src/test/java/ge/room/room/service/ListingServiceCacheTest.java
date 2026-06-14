package ge.room.room.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ge.room.room.model.Listing;
import ge.room.room.repository.ListingRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class ListingServiceCacheTest {

    @Autowired
    private ListingService listingService;

    @MockitoBean
    private ListingRepository listingRepository;

    @Test
    void findAll_usesCacheUntilEvicted() {
        Listing listing = sampleListing();
        when(listingRepository.findAll()).thenReturn(List.of(listing));

        assertThat(listingService.findAll()).hasSize(1);
        assertThat(listingService.findAll()).hasSize(1);
        verify(listingRepository, times(1)).findAll();

        listingService.save(listing);

        assertThat(listingService.findAll()).hasSize(1);
        verify(listingRepository, times(2)).findAll();
    }

    @Test
    void findById_usesCacheUntilEvicted() {
        Listing listing = sampleListing();
        when(listingRepository.findById(1L)).thenReturn(Optional.of(listing));

        assertThat(listingService.findById(1L)).contains(listing);
        assertThat(listingService.findById(1L)).contains(listing);
        verify(listingRepository, times(1)).findById(1L);

        listingService.deleteById(1L);

        assertThat(listingService.findById(1L)).contains(listing);
        verify(listingRepository, times(2)).findById(1L);
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
