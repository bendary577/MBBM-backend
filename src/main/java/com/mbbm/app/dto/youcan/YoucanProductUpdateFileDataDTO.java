package com.mbbm.app.dto.youcan;

import com.mbbm.app.dto.blobs.BlobEntityDTO;
import com.mbbm.app.enums.EBlobFileExtention;
import com.mbbm.app.enums.EBlobType;
import com.mbbm.app.model.base.Profile;

import java.util.LinkedList;
import java.util.List;

public class YoucanProductUpdateFileDataDTO extends BlobEntityDTO {

    private List<String> fileHeaders;
    private List<YoucanProductUpdateDTO> youcanProductUpdateDTOList;


    public YoucanProductUpdateFileDataDTO(EBlobType blobType, EBlobFileExtention extension, Profile profile){
        super(blobType, extension, profile);
        //initialize product update actions list
        youcanProductUpdateDTOList = new LinkedList<>();
        //instantiate file headers list
        this.fileHeaders = new LinkedList<>();
        this.fileHeaders.add("Product Code");
        this.fileHeaders.add("Product English Name");
        this.fileHeaders.add("Is Visibility Updated");
        this.fileHeaders.add("Is Price Updated");
        this.fileHeaders.add("Price Value Before");
        this.fileHeaders.add("Price Value After");
        this.fileHeaders.add("Update Time Stamp");
    }

    public List<String> getFileHeaders() {
        return fileHeaders;
    }

    public void setFileHeaders(List<String> fileHeaders) {
        this.fileHeaders = fileHeaders;
    }

    public List<YoucanProductUpdateDTO> getYoucanProductUpdateDTOList() {
        return youcanProductUpdateDTOList;
    }

    public void setYoucanProductUpdateDTOList(List<YoucanProductUpdateDTO> youcanProductUpdateDTOList) {
        this.youcanProductUpdateDTOList = youcanProductUpdateDTOList;
    }

}

