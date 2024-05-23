package com.mbbm.app.storage.concrete;

import com.mbbm.app.storage.contract.Storage;
import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class DiskStorage implements Storage {

    @Override
    public long save(byte[] content, String filePath, String fileName) throws IOException {
        if(isPathAvailable(filePath)){
            //TODO : REFACTOR THIS .. WE ALREADY MAKE A CHECK ON FILE PATH .. NO NEED TO DO AGAIN
            String separator = FileSystems.getDefault().getSeparator();
            File file = new File(filePath  + separator + fileName + ".xlsx");
            if (!file.exists()) {
                file.createNewFile();
            }
            try (OutputStream out = new FileOutputStream(file)) {
                out.write(content);
            }
        }else{
            throw new FileNotFoundException("the specified path doesn't exist on the system");
        }
        //TODO : needs more thinking if we really need this
        File file = new File(filePath);
        return file.length();
    }

    @Override
    public boolean isPathAvailable(String filePath){
        File file = new File(filePath);
        return file.exists();
    }
}
