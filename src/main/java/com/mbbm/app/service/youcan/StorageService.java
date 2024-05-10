package com.mbbm.app.service.youcan;

import com.mbbm.app.exception.storage.StorageNotConfiguredException;
import com.mbbm.app.repository.SubscriptionRepository;
import com.mbbm.app.service.SubscriptionService;
import com.mbbm.app.service.authentication.AuthenticationService;
import com.mbbm.app.util.storage.contract.Storage;
import com.mbbm.app.util.storage.contract.StorageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class StorageService {

    @Autowired
    private SubscriptionService subscriptionService;

    private Storage storage;

    private final Logger logger = LoggerFactory.getLogger(StorageService.class);

    public StorageService(){
        storage = subscriptionService.getCurrentUserSubscriptionStorage().getConfiguredStorage();
    }

    /**
     * TODO : needs enhancements
     * @param content
     * @param filePath
     * @throws Exception
     */
    public void saveBlob(byte[] content, String filePath) throws Exception{
        if(this.storage == null){
            throw new StorageNotConfiguredException("we've encountered an issue while trying to resolve your subscription storage");
        }
        try (OutputStream out = new FileOutputStream(filePath)) {
            out.write(content);
        }
    }


}
