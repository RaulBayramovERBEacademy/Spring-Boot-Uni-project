package ge.room.room.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppInfoReporter {

    private static final Logger logger = LoggerFactory.getLogger(AppInfoReporter.class);

    @Value("${app.info.name}")
    private String appNameFromValue;

    @Value("${app.info.version}")
    private String appVersionFromValue;

    private final AppInfoProperties appInfoProperties;

    public AppInfoReporter(AppInfoProperties appInfoProperties) {
        this.appInfoProperties = appInfoProperties;
    }

    @PostConstruct
    public void report() {
        logger.info("App info via @Value: name={}, version={}", appNameFromValue, appVersionFromValue);
        logger.info("App info via @ConfigurationProperties: name={}, version={}",
                appInfoProperties.getName(), appInfoProperties.getVersion());
    }
}
