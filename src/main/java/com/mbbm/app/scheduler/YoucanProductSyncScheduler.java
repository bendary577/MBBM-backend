package com.mbbm.app.scheduler;

import com.mbbm.app.model.youcan.YoucanConfiguration;
import com.mbbm.app.model.youcan.YoucanIntegration;
import com.mbbm.app.repository.YoucanIntegrationRepository;
import com.mbbm.app.service.youcan.YoucanSyncProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
public class YoucanProductSyncScheduler {

    @Autowired
    private YoucanIntegrationRepository youcanIntegrationRepository;
    @Autowired
    private YoucanSyncProductService youcanSyncProductService;

    @Async("asyncTaskExecutor")
//    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void scheduledTask() {
        List<YoucanIntegration> youcanIntegrationRepositoryList = youcanIntegrationRepository.findAll();
        for(YoucanIntegration youcanIntegration : youcanIntegrationRepositoryList){
            YoucanConfiguration youcanConfiguration = youcanIntegration.getYoucanConfiguration();
            if(youcanConfiguration != null && youcanConfiguration.isSyncProducts()){
                Timestamp lastSyncTime = youcanConfiguration.getLastSyncTime();
                Instant lastSyncTimeInstant;
                if(lastSyncTime != null){
                    lastSyncTimeInstant = Instant.parse(lastSyncTime.toString());
                }else{
                    lastSyncTimeInstant = Instant.now();
                }
                Instant currentTimestampInstant = Instant.now();
                Duration syncDurationPeriod = youcanConfiguration.getSyncDurationInSeconds();
                boolean hasExceededDuration = lastSyncTimeInstant.plus(syncDurationPeriod).isBefore(currentTimestampInstant);
                if (hasExceededDuration) { // needs to run the sync
                    youcanSyncProductService.syncProducts(youcanIntegration, youcanConfiguration);
                } else {
                    System.out.println("The timestamp is within the specified duration.");
                }

            }
        }
        System.out.println("Scheduled task executed at: " + System.currentTimeMillis());
    }
}
