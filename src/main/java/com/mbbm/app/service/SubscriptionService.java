package com.mbbm.app.service;

import com.mbbm.app.enums.EStorageType;
import com.mbbm.app.enums.ESubscriptionType;
import com.mbbm.app.exception.subscription.ProfileHasNoSubscriptionException;
import com.mbbm.app.model.base.*;
import com.mbbm.app.repository.SubscriptionRepository;
import com.mbbm.app.service.authentication.AuthenticationService;
import com.mbbm.app.storage.contract.StorageFactory;
import com.mbbm.app.storage.factory.CloudStorageFactory;
import com.mbbm.app.storage.factory.DiskStorageFactory;
import org.openqa.selenium.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private SubscriptionRepository SubscriptionRepository;

    private final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    public Subscription findByName(ESubscriptionType subscription){
        Optional<Subscription> subscriptionOptional =  SubscriptionRepository.findByName(subscription);
        return subscriptionOptional.orElse(null);
    }

    public void save(Subscription subscription){
        SubscriptionRepository.save(subscription);
    }
    
    public void initApplicationSubscriptions(){
        Subscription subscription = null;
        for(ESubscriptionType subscriptionType : ESubscriptionType.values()){
            if(findByName(subscriptionType) == null){
                switch(subscriptionType){
                    case MONTHLY:
                        subscription = new Subscription();
                        subscription.setName(ESubscriptionType.MONTHLY);
                        subscription.setPrice(20D);
                        subscription.setStorageType(EStorageType.DISK);
                        save(subscription);
                        break;
                    case YEARLY:
                        subscription = new Subscription();
                        subscription.setName(ESubscriptionType.YEARLY);
                        subscription.setPrice(100D);
                        subscription.setStorageType(EStorageType.CLOUD);
                        save(subscription);
                        break;
                    case ONE_TIME_PAYMENT:
                        subscription = new Subscription();
                        subscription.setName(ESubscriptionType.ONE_TIME_PAYMENT);
                        subscription.setPrice(10000D);
                        subscription.setStorageType(EStorageType.CLOUD);
                        save(subscription);
                        break;
                    default:
                        //TODO :: handle this exception properly
                        System.out.println("throw an exception");
                }

            }
        }
    }

    private EStorageType getCurrentUserSubscriptionStorageType(){
        Profile profile = authenticationService.getCurrentAuthenticatedUserProfile();
        if(profile == null){
            throw new NotFoundException("The provided profile id is not found");
        }
        Subscription subscription = profile.getSubscription();
        if(subscription == null){
           throw new ProfileHasNoSubscriptionException("the current profile doesn't have any subscription at all");
        }
        return subscription.getStorageType();
    }

    public StorageFactory getCurrentUserSubscriptionStorage(){
        StorageFactory storageFactory = null;
        try{
            EStorageType storageType = getCurrentUserSubscriptionStorageType();
            if(storageType != null){
                switch(storageType){
                    case CLOUD:
                        storageFactory = new CloudStorageFactory();
                        break;
                    case DISK:
                        storageFactory = new DiskStorageFactory();
                        break;
                    default:
                        //TODO :: handle this exception properly
                        System.out.println("throw an exception");
                }
            }
        }catch (Exception exception){
            //TODO : rethink of the below solution .. handle this exception
            //didn't find subscription storage for any reason
            storageFactory = new DiskStorageFactory();
        }
        return storageFactory;
    }
}
