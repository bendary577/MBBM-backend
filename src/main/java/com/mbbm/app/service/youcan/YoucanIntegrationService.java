package com.mbbm.app.service.youcan;

import com.google.gson.Gson;
import com.mbbm.app.enums.ESyncStatus;
import com.mbbm.app.enums.EYoucanPricingStrategy;
import com.mbbm.app.enums.EYoucanSyncType;
import com.mbbm.app.exception.youcan.YoucanNotIntegratedException;
import com.mbbm.app.model.base.Profile;
import com.mbbm.app.model.youcan.YouCanStore;
import com.mbbm.app.model.youcan.YoucanConfiguration;
import com.mbbm.app.model.youcan.YoucanIntegration;
import com.mbbm.app.service.authentication.AuthenticationService;
import com.mbbm.app.service.ProfileService;
import com.mbbm.app.util.compression.CompressionUtility;
import com.mbbm.app.youcan.YoucanSettings;
import com.mbbm.app.youcan.dto.integration.YoucanIntegrationLoginResponseDTO;
import com.mbbm.app.youcan.dto.integration.YoucanLoginRequestDTO;
import com.mbbm.app.youcan.dto.integration.YoucanStoreDTO;
import com.mbbm.app.repository.YoucanIntegrationRepository;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class YoucanIntegrationService {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private YoucanIntegrationRepository youcanIntegrationRepository;

    @Autowired
    private YoucanConfigurationService youcanConfigurationService;

    @Autowired
    private YoucanStoreService youcanStoreService;

    @Autowired
    private CompressionUtility compressionUtility;

    @Autowired
    private AuthenticationService authenticationService;

    public YoucanIntegrationService(){}

    public YoucanIntegration integrateYoucan(@NotNull YoucanLoginRequestDTO youcanLoginRequestDTO) throws Exception {
        Profile profile = authenticationService.getCurrentAuthenticatedUserProfile();
        if(profile == null){
            throw new NotFoundException("The provided profile id is not found");
        }
        YoucanIntegration youcanIntegration = profile.getYoucanIntegration();
        if(youcanIntegration != null){
            return youcanIntegration;
        }

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("email", youcanLoginRequestDTO.getUsername())
                .addFormDataPart("password", youcanLoginRequestDTO.getPassword())
                .build();

        Request request = new Request.Builder()
                .url(YoucanSettings.YOUCAN_BASE_API_URL + "/auth/login")
                .post(requestBody)
                .addHeader("Accept", "application/json")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            Gson gson = new Gson();
            String decoredResponseBody = compressionUtility.decode(response.body().bytes());
            YoucanIntegrationLoginResponseDTO youcanIntegrationLoginResponseDTO = gson.fromJson(decoredResponseBody, YoucanIntegrationLoginResponseDTO.class);
            return saveYoucanIntegration(youcanIntegrationLoginResponseDTO, profile);
        } else {
            throw new YoucanNotIntegratedException("we've encountered an error while trying to integrate with your youcan store");
        }
    }

    @Transactional
    public YoucanIntegration saveYoucanIntegration(YoucanIntegrationLoginResponseDTO youcanIntegrationLoginResponseDTO, Profile profile){
        YoucanIntegration youcanIntegration = new YoucanIntegration();
        //TODO : this should be encrypted before saving
        youcanIntegration.setToken(youcanIntegrationLoginResponseDTO.getToken());
        youcanIntegration.setExpiredAt(youcanIntegrationLoginResponseDTO.getExpired_at());
        youcanIntegration.setProfile(profile);
        youcanIntegrationRepository.save(youcanIntegration);
        //save list of youcan integration stores
        List<YouCanStore> youCanStoreList = new LinkedList<>();
        for(YoucanStoreDTO youcanStoreDTO : youcanIntegrationLoginResponseDTO.getStores()){
            YouCanStore youCanStore = new YouCanStore();
            youCanStore.setStoreId(youcanStoreDTO.getStore_id());
            youCanStore.setSlug(youcanStoreDTO.getSlug());
            youCanStore.setActive(youcanStoreDTO.isIs_active());
            youCanStore.setEmailVerified(youcanStoreDTO.isIs_email_verified());
            youCanStore.setYoucanIntegration(youcanIntegration);
            youCanStoreList.add(youCanStore);
        }
        youcanStoreService.saveYoucanStoreList(youCanStoreList);
        //save youcan integration configurations
        YoucanConfiguration youcanConfiguration = new YoucanConfiguration();
        youcanConfiguration.setLastSyncStatus(ESyncStatus.NOT_SYNCED);
        youcanConfiguration.setPricingStrategy(EYoucanPricingStrategy.RATE_FOR_ALL);
        youcanConfiguration.setSyncProducts(false);
        youcanConfiguration.setSyncType(EYoucanSyncType.GET);
        youcanConfiguration.setUpdateVisibility(false);
        youcanConfiguration.setUpdatePrices(false);
        youcanConfiguration.setIntegration(youcanIntegration);
        youcanConfigurationService.saveYoucanConfiguration(youcanConfiguration);
        return youcanIntegration;
    }

    public YoucanIntegration getUserProfileYoucanIntegration() throws Exception{
        Profile profile = authenticationService.getCurrentAuthenticatedUserProfile();
        if(profile == null){
            throw new NotFoundException("The provided profile id is not found");
        }

        YoucanIntegration youcanIntegration = profile.getYoucanIntegration();
        if(youcanIntegration == null){
            throw new YoucanNotIntegratedException("the current profile is not integrated with youcan");
        }
        return youcanIntegration;
    }

}
