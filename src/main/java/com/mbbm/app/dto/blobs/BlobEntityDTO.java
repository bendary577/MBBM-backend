package com.mbbm.app.dto.blobs;

import com.mbbm.app.enums.EBlobFileExtention;
import com.mbbm.app.enums.EBlobType;
import com.mbbm.app.model.base.Profile;

public abstract class BlobEntityDTO {

    private EBlobType fileType;
    private EBlobFileExtention fileExtension;
    private String fileName;
    private String filePath;
    private String fileSize;
    private Profile profile;

    public BlobEntityDTO(EBlobType fileType, EBlobFileExtention fileExtension, Profile profile){
        this.fileType = fileType;
        this.fileExtension = fileExtension;
        this.profile = profile;
    }

    public EBlobType getFileType() {
        return fileType;
    }

    public void setFileType(EBlobType fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public EBlobFileExtention getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(EBlobFileExtention fileExtension) {
        this.fileExtension = fileExtension;
    }
}

