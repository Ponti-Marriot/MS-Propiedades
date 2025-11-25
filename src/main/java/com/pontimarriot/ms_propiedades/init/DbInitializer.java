// java
package com.pontimarriot.ms_propiedades.init;

import com.pontimarriot.ms_propiedades.Repositories.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements ApplicationRunner {

    private final LocationRepository locationRepository;
    private final Logger logger = LoggerFactory.getLogger(DbInitializer.class);

    public DbInitializer(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.info("DB initializer: fetching locations from external service...");
        try {
            locationRepository.fetchAndSaveAll()
                    .doOnSuccess(v -> logger.info("DB initializer: locations fetched and saved"))
                    .doOnError(e -> logger.warn("DB initializer: failed to fetch/save locations", e))
                    .block();
        } catch (Exception e) {
            logger.warn("DB initializer: interrupted or failed", e);
        }
    }
}
