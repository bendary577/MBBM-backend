package com.mbbm.app.service.youcan;

import com.mbbm.app.exception.storage.StorageNotConfiguredException;
import com.mbbm.app.service.SubscriptionService;
import com.mbbm.app.storage.contract.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    private SubscriptionService subscriptionService;

    private final Storage storage;

    private final Logger logger = LoggerFactory.getLogger(StorageService.class);

    @Autowired
    public StorageService(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
        //use abstract factory pattern to get each user's subscription storage type
        storage = subscriptionService.getCurrentUserSubscriptionStorage().getConfiguredStorage();
    }

    /**
     * TODO : needs enhancements
     * @param content
     * @param filePath
     * @throws Exception
     */
    public long saveBlob(byte[] content, String filePath, String fileName) throws Exception{
        if(this.storage == null){
            throw new StorageNotConfiguredException("we've encountered an issue while trying to resolve your subscription storage");
        }
        if(content == null){
            throw new IllegalArgumentException("the provided file content to be saved in storage is null");
        }
        return storage.save(content, filePath, fileName);
    }

}
