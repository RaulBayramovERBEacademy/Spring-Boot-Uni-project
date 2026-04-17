package ge.room.room;
import ge.room.room.model.Listing;
import ge.room.room.service.ListingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final ListingService listingService;

    public HomeController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public String index(Model model) {
        if (!model.containsAttribute("listing")) {
            model.addAttribute("listing", new Listing());
        }
        model.addAttribute("listings", listingService.findAll());
        logger.info("Home page loaded");
        logger.debug("Detailed debug log");
        return "index";
    }

    @PostMapping("/listings")
    public String createListing(@Valid @ModelAttribute("listing") Listing listing, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listings", listingService.findAll());
            logger.warn("Listing validation failed");
            return "index";
        }

        String fileName = listing.getFile() != null ? listing.getFile().getOriginalFilename() : "no-file";
        Listing savedListing = listingService.save(listing);
        logger.info("New listing created. id={}, title={}, file={}", savedListing.getId(), savedListing.getTitle(), fileName);
        return "redirect:/";
    }
}
