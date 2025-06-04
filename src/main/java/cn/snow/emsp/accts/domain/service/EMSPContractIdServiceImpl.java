package cn.snow.emsp.accts.domain.service;

import cn.snow.emsp.accts.domain.common.CompactSnowflake;

public class EMSPContractIdServiceImpl implements EMSPContractIdService {

    private final String countryCode;
    private final String companyCode;
    private final CompactSnowflake compactSnowflake;

    public EMSPContractIdServiceImpl(String countryCode, String companyCode, int workerId) {
        this.countryCode = countryCode;
        this.companyCode = companyCode;
        this.compactSnowflake = new CompactSnowflake(workerId);
    }

    @Override
    public String generateContractId() {
        return countryCode + companyCode + compactSnowflake.nextId();
    }
}
