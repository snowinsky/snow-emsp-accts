package cn.snow.emsp.accts.domain.service.events;

import cn.snow.emsp.accts.domain.model.Card;

public class CardAssignEvent {

    private final Card card;

    public CardAssignEvent(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return String.format("Card(number=%s) was assign to Account(email=%s) on %s", card.getRfid().getVisibleNumber(), card.getAccountEmail().getValue(), card.getLastUpdated() );
    }
}
