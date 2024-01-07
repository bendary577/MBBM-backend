package com.mbbm.app.util.compression;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class CompressionUtility {

    public String decode(byte[] encodedContent) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(encodedContent);
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String line;
        final StringBuilder outStr = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            outStr.append(line);
        }
        return outStr.toString();
    }
}
