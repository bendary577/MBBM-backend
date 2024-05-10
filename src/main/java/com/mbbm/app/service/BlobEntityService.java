package com.mbbm.app.service;

import com.mbbm.app.model.base.BlobEntity;
import com.mbbm.app.repository.BlobEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlobEntityService {

    @Autowired
    private BlobEntityRepository blobEntityRepository;

    public void save(BlobEntity blobEntity){
        blobEntityRepository.save(blobEntity);
    }

    public void createBlobEntity(){
        BlobEntity blobEntity = new BlobEntity();
        save(blobEntity);
    }


}
