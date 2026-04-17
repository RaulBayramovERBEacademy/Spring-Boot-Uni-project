package ge.room.room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
   
    @RequestMapping("/")
    public String index() {
        logger.info("Home page loaded");
        logger.debug("Detailed debug log");
        return "index.html";
    }
}
