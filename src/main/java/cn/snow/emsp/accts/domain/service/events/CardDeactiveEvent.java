package cn.snow.emsp.accts.domain.service.events;

import cn.snow.emsp.accts.domain.model.Card;

public class CardDeactiveEvent {

    private final Card card;

    public CardDeactiveEvent(Card card) {
        this.card = card;
    }
}
