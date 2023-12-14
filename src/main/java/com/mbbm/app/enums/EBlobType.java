package com.mbbm.app.enums;

public enum EBlobType {
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

    private EBlobType(int value) {
        this.value = value;
    }
}
