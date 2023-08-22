package com.am53.brand.hub.onboarding.taponphone.service;

import com.am53.brand.hub.onboarding.taponphone.dto.TapOnPhoneMerchantRequest;
import com.am53.brand.hub.onboarding.taponphone.dto.TapOnPhoneMerchantResponse;
import com.am53.brand.hub.onboarding.taponphone.model.MerchantPaycoreTapOnPhone;
import com.am53.brand.hub.onboarding.taponphone.model.MerchantPaycoreTapOnPhoneId;
import com.am53.brand.hub.onboarding.taponphone.repository.MerchantPaycoreTapOnPhoneRepository;
import com.am53.brand.hub.onboarding.taponphone.utils.EnumTapOnPhoneMerchant;
import org.apache.hc.client5.http.auth.AuthStateCacheable;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class TapOnPhoneServiceImpl implements TapOnPhoneService {

    private static final Logger log = LoggerFactory.getLogger(TapOnPhoneServiceImpl.class);

    @Value("${values.saas-id}")
    private String saasId;

    private final ModelMapper modelMapper;

    private final MerchantPaycoreTapOnPhoneRepository merchantPaycoreTapOnPhoneRepository;

    @Autowired
    public TapOnPhoneServiceImpl(MerchantPaycoreTapOnPhoneRepository merchantPaycoreTapOnPhoneRepository, ModelMapper modelMapper) {
        this.merchantPaycoreTapOnPhoneRepository = merchantPaycoreTapOnPhoneRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TapOnPhoneMerchantRequest createOrUpdateTapOnPhone(TapOnPhoneMerchantRequest request) {

        log.info("Agrega o actualiza ");

        MerchantPaycoreTapOnPhoneId requestId = new MerchantPaycoreTapOnPhoneId(this.saasId, request.getMidPaycore());

        Optional<MerchantPaycoreTapOnPhone> merchantOptional = merchantPaycoreTapOnPhoneRepository.findById(requestId);

        if (merchantOptional.isPresent()) {

            MerchantPaycoreTapOnPhone merchant = modelMapper.map(request, MerchantPaycoreTapOnPhone.class);
            merchant.setId(requestId);
            merchant.setCreateDate(new Date());

            merchantPaycoreTapOnPhoneRepository.save(merchant);

            log.info("El registro se ha actualizado correctamente");

        } else {

            // Si el merchant no existe en la base de datos, crea uno nuevo
            MerchantPaycoreTapOnPhone newMerchant = new MerchantPaycoreTapOnPhone();

            newMerchant.setId(requestId);

            Long tidPaycore = Long.valueOf(request.getTidPaycore());
            newMerchant.setTidPaycore(tidPaycore);
            newMerchant.setStatus(request.getStatus().ordinal());
            newMerchant.setCreateDate(new Date());

            // Guarda el nuevo merchant en la base de datos
            merchantPaycoreTapOnPhoneRepository.save(newMerchant);

            log.info("El registro se ha GUARDADO correctamente.");

        }

        return request;

    }

    @Override
    public TapOnPhoneMerchantResponse findTapOnPhone(String midPaycore) {

        log.info("Find a record");

        TapOnPhoneMerchantResponse tapOnPhoneMerchantResponse = new TapOnPhoneMerchantResponse();

        MerchantPaycoreTapOnPhoneId requestId = new MerchantPaycoreTapOnPhoneId(this.saasId, midPaycore);

        Optional<MerchantPaycoreTapOnPhone> merchantOptional = merchantPaycoreTapOnPhoneRepository.findById(requestId);

        if (merchantOptional.isPresent()) {

            log.info("The record with ID: {} has been found.", midPaycore);

            tapOnPhoneMerchantResponse.setMidPaycore(merchantOptional.get().getId().getMidPaycore());

            String tidPaycore = String.valueOf(merchantOptional.get().getTidPaycore());
            tapOnPhoneMerchantResponse.setCodigoTerminal(tidPaycore);

            tapOnPhoneMerchantResponse.setStatus(EnumTapOnPhoneMerchant.fromStatus(merchantOptional.get().getStatus()));

            tapOnPhoneMerchantResponse.setCreateDate(merchantOptional.get().getCreateDate());

            return tapOnPhoneMerchantResponse;

        } else {

            log.info("The record with ID: {} was not found.", midPaycore);

            return null;
        }

    }

}