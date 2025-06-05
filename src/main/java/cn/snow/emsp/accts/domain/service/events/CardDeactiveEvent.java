package cn.snow.emsp.accts.domain.service.events;

import cn.snow.emsp.accts.domain.model.Card;

public class CardDeactiveEvent {

    private final Card card;

    public CardDeactiveEvent(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return String.format("Card(number=%s) was deactivated on %s", card.getRfid().getVisibleNumber(), card.getLastUpdated() );
    }
}
