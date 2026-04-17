package ge.room.room.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DevOnlyConfig {

    private static final Logger logger = LoggerFactory.getLogger(DevOnlyConfig.class);

    @Bean
    @Profile("dev")
    public DevOnlyBean devOnlyBean() {
        logger.info("devOnlyBean is active because 'dev' profile is enabled.");
        return new DevOnlyBean("dev-only-bean");
    }

    public record DevOnlyBean(String name) {
    }
}
