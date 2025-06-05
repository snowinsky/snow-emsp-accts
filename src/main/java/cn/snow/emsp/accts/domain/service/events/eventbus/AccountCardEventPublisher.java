package cn.snow.emsp.accts.domain.service.events.eventbus;

@FunctionalInterface
public interface AccountCardEventPublisher {
    void publishEvent(Object event);
}
