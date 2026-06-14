package ge.room.room.task;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ge.room.room.model.Listing;
import ge.room.room.service.ListingService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListingReportTaskTest {

    @Mock
    private ListingService listingService;

    @InjectMocks
    private ListingReportTask listingReportTask;

    @Test
    void reportListingCount_readsListingsFromService() {
        Listing listing = new Listing();
        listing.setId(1L);
        listing.setTitle("Cozy Studio");
        listing.setDescription("Bright room near the center");
        listing.setType("studio");
        listing.setCity("Tbilisi");
        listing.setPrice(450.0);
        when(listingService.findAll()).thenReturn(List.of(listing));

        listingReportTask.reportListingCount();

        verify(listingService).findAll();
    }
}
