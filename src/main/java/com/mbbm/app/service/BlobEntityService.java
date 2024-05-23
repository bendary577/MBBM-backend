package com.mbbm.app.service;

import com.mbbm.app.dto.blobs.BlobEntityDTO;
import com.mbbm.app.model.base.BlobEntity;
import com.mbbm.app.repository.BlobEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlobEntityService {

    @Autowired
    private BlobEntityRepository blobEntityRepository;

    public void save(BlobEntity blobEntity){
        blobEntityRepository.save(blobEntity);
    }

    public void createBlobEntity(BlobEntityDTO blobEntityDTO){
        BlobEntity blobEntity = new BlobEntity();
        blobEntity.setName(blobEntityDTO.getFileName());
        blobEntity.setPath(blobEntityDTO.getFilePath());
        blobEntity.setSize(blobEntity.getSize());
        blobEntity.setType(blobEntityDTO.getFileType());
        blobEntity.setProfile(blobEntityDTO.getProfile());
        blobEntity.setTimestamp(String.valueOf(new Date().getTime()));
        save(blobEntity);
    }


}
