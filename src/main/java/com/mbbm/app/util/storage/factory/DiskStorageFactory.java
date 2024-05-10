package com.mbbm.app.util.storage.factory;

import com.mbbm.app.util.storage.concrete.DiskStorage;
import com.mbbm.app.util.storage.contract.Storage;
import com.mbbm.app.util.storage.contract.StorageFactory;

public class DiskStorageFactory implements StorageFactory {

    @Override
    public Storage getConfiguredStorage() {
        return new DiskStorage();
    }
}
