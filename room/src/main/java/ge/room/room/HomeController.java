package ge.room.room;
import ge.room.room.model.Listing;
import ge.room.room.service.ListingService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final ListingService listingService;

    @Value("${room.upload-dir:uploads}")
    private String uploadDir;

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

        applyUploadedImage(listing);    
        String fileName = listing.getFile() != null ? listing.getFile().getOriginalFilename() : "no-file";
        Listing savedListing = listingService.save(listing);
        logger.info("New listing created. id={}, title={}, imageUrl={}, file={}",
                savedListing.getId(), savedListing.getTitle(), savedListing.getImageUrl(), fileName);
        return "redirect:/";
    }

    private void applyUploadedImage(Listing listing) {
        MultipartFile file = listing.getFile();
        if (file == null || file.isEmpty()) {
            return;
        }

        try {
            Path uploadsPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadsPath);

            String extension = extractSafeExtension(file.getOriginalFilename());
            String storedFileName = UUID.randomUUID() + extension;
            Path target = uploadsPath.resolve(storedFileName);

            file.transferTo(target.toFile());
            listing.setImageUrl("/uploads/" + storedFileName);
        } catch (IOException e) {
            logger.error("Failed to save uploaded file", e);
        }
    }

    private String extractSafeExtension(String originalFilename) {
        if (originalFilename == null || !originalFilename.contains(".")) {
            return "";
        }

        String ext = originalFilename.substring(originalFilename.lastIndexOf('.')).toLowerCase(Locale.ROOT);
        if (ext.matches("\\.[a-z0-9]{1,8}")) {
            return ext; 
            
        }
        return "";
    }
}
