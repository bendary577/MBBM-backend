package com.mbbm.app.storage.factory;

import com.mbbm.app.storage.concrete.DiskStorage;
import com.mbbm.app.storage.contract.StorageFactory;
import com.mbbm.app.storage.contract.Storage;

public class DiskStorageFactory implements StorageFactory {

    @Override
    public Storage getConfiguredStorage() {
        return new DiskStorage();
    }
}
