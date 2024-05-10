package com.mbbm.app.util.storage.factory;

import com.mbbm.app.util.storage.concrete.CloudStorage;
import com.mbbm.app.util.storage.contract.Storage;
import com.mbbm.app.util.storage.contract.StorageFactory;

public class CloudStorageFactory implements StorageFactory {

    @Override
    public Storage getConfiguredStorage() {
        return new CloudStorage();
    }
}
