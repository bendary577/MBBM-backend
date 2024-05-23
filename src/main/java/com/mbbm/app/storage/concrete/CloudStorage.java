package com.mbbm.app.storage.concrete;

import com.mbbm.app.storage.contract.Storage;
import java.io.*;

public class CloudStorage implements Storage {

    @Override
    public long save(byte[] content, String filePath, String fileName) throws IOException {
        return 0L;
    }

    @Override
    public boolean isPathAvailable(String path) {
        return false;
    }
}
