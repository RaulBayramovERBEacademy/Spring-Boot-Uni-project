package ge.room.room.task;

import ge.room.room.service.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ListingReportTask {

    private static final Logger logger = LoggerFactory.getLogger(ListingReportTask.class);

    private final ListingService listingService;

    public ListingReportTask(ListingService listingService) {
        this.listingService = listingService;
    }

    @Scheduled(
            fixedRateString = "${room.schedule.listing-report.fixed-rate-ms:300000}",
            initialDelayString = "${room.schedule.listing-report.initial-delay-ms:300000}")
    public void reportListingCount() {
        int count = listingService.findAll().size();
        logger.info("Scheduled listing report: {} active listing(s)", count);
    }
}
