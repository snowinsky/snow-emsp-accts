package cn.snow.emsp.accts.domain.service;

import cn.snow.emsp.accts.domain.common.EMAIDValidator;

public interface EMSPContractIdService {

    String generateContractId();

    default String generateNormalizedContractId() {
        String contractId = generateContractId();
        if (EMAIDValidator.isNormalized(contractId)) {
            return contractId;
        } else {
            return EMAIDValidator.normalize(contractId);
        }
    }
}
