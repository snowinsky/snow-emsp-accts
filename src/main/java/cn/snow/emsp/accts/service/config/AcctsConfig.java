package cn.snow.emsp.accts.service.config;

import cn.snow.emsp.accts.domain.service.EMSPContractIdService;
import cn.snow.emsp.accts.domain.service.EMSPContractIdServiceImpl;
import cn.snow.emsp.accts.domain.service.events.eventbus.AccountCardEventPublisher;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class AcctsConfig {

    @Value("${emsp.contractId.countryCode:CN}")
    private String countryCode;
    @Value("${emsp.contractId.companyCode:ABC}")
    private String companyCode;
    @Value("${emsp.contractId.workerId:1}")
    private Integer workerId;


    @Bean
    public EMSPContractIdService emspContractIdService() {
        return new EMSPContractIdServiceImpl(countryCode, companyCode, workerId);
    }

    @Bean
    public AccountCardEventPublisher accountCardEventPublisher() {
        EventBus eventBus1 = new EventBus();
        eventBus1.register(new AcctsEventListener());
        return eventBus1::post;
    }
}
