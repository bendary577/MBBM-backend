package com.mbbm.app.storage.factory;

import com.mbbm.app.storage.concrete.CloudStorage;
import com.mbbm.app.storage.contract.StorageFactory;
import com.mbbm.app.storage.contract.Storage;

public class CloudStorageFactory implements StorageFactory {

    @Override
    public Storage getConfiguredStorage() {
        return new CloudStorage();
    }
}
