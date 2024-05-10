package com.mbbm.app.service.youcan;

import com.mbbm.app.model.youcan.YouCanStore;
import com.mbbm.app.repository.YoucanStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YoucanStoreService {

    @Autowired
    private YoucanStoreRepository youcanStoreRepository;

    public YoucanStoreService(){}

    public void saveYoucanStore(YouCanStore youCanStore){
        this.youcanStoreRepository.save(youCanStore);
    }

    public void saveYoucanStoreList(List<YouCanStore> youCanStoreList){
        this.youcanStoreRepository.saveAll(youCanStoreList);
    }


}
