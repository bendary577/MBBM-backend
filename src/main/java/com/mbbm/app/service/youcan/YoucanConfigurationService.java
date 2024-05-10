package com.mbbm.app.service.youcan;

import com.mbbm.app.model.youcan.YoucanConfiguration;
import com.mbbm.app.repository.YoucanConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YoucanConfigurationService {

    @Autowired
    private YoucanConfigurationRepository youcanConfigurationRepository;

    public YoucanConfigurationService(){}

    public void saveYoucanConfiguration(YoucanConfiguration youcanConfiguration){
        this.youcanConfigurationRepository.save(youcanConfiguration);
    }


}
