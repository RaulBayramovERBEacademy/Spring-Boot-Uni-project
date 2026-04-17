package ge.room.room;
import ge.room.room.model.Listing;
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

    @GetMapping
    public String index(Model model) {
        if (!model.containsAttribute("listing")) {
            model.addAttribute("listing", new Listing());
        }
        logger.info("Home page loaded");
        logger.debug("Detailed debug log");
        return "index";
    }

    @PostMapping("/listings")
    public String createListing(@Valid @ModelAttribute("listing") Listing listing, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("Listing validation failed");
            return "index";
        }

        String fileName = listing.getFile() != null ? listing.getFile().getOriginalFilename() : "no-file";
        logger.info("New listing created. title={}, file={}", listing.getTitle(), fileName);
        return "redirect:/";
    }
}
