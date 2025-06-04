package cn.snow.emsp.accts.domain.service.events;

import cn.snow.emsp.accts.domain.model.Card;

public class CardAssignEvent {

    private final Card card;

    public CardAssignEvent(Card card) {
        this.card = card;
    }
}
