package cn.snow.emsp.accts.domain.service.events;

import cn.snow.emsp.accts.domain.model.Card;

public class CardActiveEvent {

    private final Card card;

    public CardActiveEvent(Card card) {
        this.card = card;
    }
}
