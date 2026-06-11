package ge.room.room.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ge.room.room.model.Listing;
import ge.room.room.service.ListingService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ListingRestControllerTest {

    @Mock
    private ListingService listingService;

    @InjectMocks
    private ListingRestController controller;

    @Test
    void getAllListings_returnsListFromService() {
        List<Listing> listings = List.of(sampleListing());
        when(listingService.findAll()).thenReturn(listings);

        List<Listing> result = controller.getAllListings();

        assertThat(result).containsExactlyElementsOf(listings);
        verify(listingService).findAll();
    }

    @Test
    void getListingById_whenFound_returnsOkWithBody() {
        Listing listing = sampleListing();
        when(listingService.findById(1L)).thenReturn(Optional.of(listing));

        ResponseEntity<Listing> response = controller.getListingById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(listing);
        verify(listingService).findById(1L);
    }

    @Test
    void getListingById_whenMissing_returnsNotFound() {
        when(listingService.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Listing> response = controller.getListingById(99L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
        verify(listingService).findById(99L);
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
