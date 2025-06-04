package cn.snow.emsp.accts.domain.service.events;

import cn.snow.emsp.accts.domain.model.Card;

public class CardCreateEvent {

    private final Card card;

    public CardCreateEvent(Card card) {
        this.card = card;
    }
}
