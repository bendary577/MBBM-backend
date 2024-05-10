package com.mbbm.app.dto.youcan;

import com.mbbm.app.enums.EBlobType;

import java.util.LinkedList;
import java.util.List;

public class YoucanProductUpdateFileDataDTO {

    private List<String> fileHeaders;
    private List<YoucanProductUpdateDTO> youcanProductUpdateDTOList;
    private EBlobType fileType;
    private String fileName;

    public YoucanProductUpdateFileDataDTO(){
        //instantiate file headers list
        fileHeaders = new LinkedList<>();
        fileHeaders.add("Product Code");
        fileHeaders.add("Product English Name");
        fileHeaders.add("Is Visibility Updated");
        fileHeaders.add("Is Price Updated");
        fileHeaders.add("Price Value Before");
        fileHeaders.add("Price Value After");
        fileHeaders.add("Update Time Stamp");
    }

    public List<YoucanProductUpdateDTO> getYoucanProductUpdateDTOList() {
        return youcanProductUpdateDTOList;
    }

    public void setYoucanProductUpdateDTOList(List<YoucanProductUpdateDTO> youcanProductUpdateDTOList) {
        this.youcanProductUpdateDTOList = youcanProductUpdateDTOList;
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

    public List<String> getFileHeaders() {
        return fileHeaders;
    }

    public void setFileHeaders(List<String> fileHeaders) {
        this.fileHeaders = fileHeaders;
    }
}

