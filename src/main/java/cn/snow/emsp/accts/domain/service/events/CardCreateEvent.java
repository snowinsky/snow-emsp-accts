package cn.snow.emsp.accts.domain.service.events;

import cn.snow.emsp.accts.domain.model.Card;

public class CardCreateEvent {

    private final Card card;

    public CardCreateEvent(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return String.format("Card(number=%s) was created on %s", card.getRfid().getVisibleNumber(), card.getLastUpdated() );
    }
}
