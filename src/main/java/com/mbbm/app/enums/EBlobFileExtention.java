package com.mbbm.app.enums;

import java.util.Map;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum EBlobFileExtention {
    TXT(0),
    PDF(1),
    JPG(2),
    PNG(3),
    XLSX(4),
    DOCX(5),
    MP4(6),
    MP3(7),
    CSV(8);

    public final int value;

    private final static Map<Integer, EBlobFileExtention> blobFileExtentionMap =
            stream(EBlobFileExtention.values()).collect(toMap(blobFileExtention -> blobFileExtention.value, blobFileExtention -> blobFileExtention));


    private EBlobFileExtention(int value) {
        this.value = value;
    }

    public static EBlobFileExtention valueOf(int blobFileExtention) {
        return blobFileExtentionMap.get(blobFileExtention);
    }
}
