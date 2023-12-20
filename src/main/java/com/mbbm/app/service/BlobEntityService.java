package com.mbbm.app.service;

import com.mbbm.app.model.base.BlobEntity;
import com.mbbm.app.repository.BlobEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class BlobEntityService {

    @Autowired
    private BlobEntityRepository blobEntityRepository;

    public void save(BlobEntity blobEntity){
        blobEntityRepository.save(blobEntity);
    }


}
