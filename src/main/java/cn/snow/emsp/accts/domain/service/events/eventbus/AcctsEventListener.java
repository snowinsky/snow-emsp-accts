package cn.snow.emsp.accts.domain.service.events.eventbus;

import cn.snow.emsp.accts.domain.service.events.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcctsEventListener {
    @Getter
    public static final EventBus eventBus = new EventBus();

    public AcctsEventListener(){
        eventBus.register(this);
    }

    @Subscribe
    public void consume(AccountActiveEvent event){
        log.info("AccountActiveEvent consumed: {}", event);
    }

    @Subscribe
    public void consume(AccountCreatedEvent event){
        log.info("AccountCreatedEvent consumed: {}", event);
    }

    @Subscribe
    public void consume(AccountDeactiveEvent event){
        log.info("AccountDeactiveEvent consumed: {}", event);
    }

    @Subscribe
    public void consume(CardActiveEvent event){
        log.info("CardActiveEvent consumed: {}", event);
    }

    @Subscribe
    public void consume(CardAssignEvent event){
        log.info("CardAssignEvent consumed: {}", event);
    }

    @Subscribe
    public void consume(CardCreateEvent event){
        log.info("CardCreateEvent consumed: {}", event);
    }

    @Subscribe
    public void consume(CardDeactiveEvent event){
        log.info("CardDeactiveEvent consumed: {}", event);
    }


}
