package com.mbbm.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MBBMAppConfiguration {

    @Autowired
    private Environment env;

    public String getBaseStoragePath() {
        String storageBasePath = env.getProperty("mbbm.base_storage_path");
        return storageBasePath;
    }

}
