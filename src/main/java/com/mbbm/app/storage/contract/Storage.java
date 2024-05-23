package com.mbbm.app.storage.contract;

import java.io.IOException;

public interface Storage {

    long save(byte[] content, String filePath, String fileName) throws IOException;

    boolean isPathAvailable(String path);
}
