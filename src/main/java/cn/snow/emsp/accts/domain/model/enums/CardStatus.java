package cn.snow.emsp.accts.domain.model.enums;

import cn.snow.emsp.accts.domain.model.Account;
import cn.snow.emsp.accts.domain.model.Card;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum CardStatus {

    CREATED(0, "Created") {
        @Override
        public void assign(Card card, Account account) {
            if(account.getStatus() != AccountStatus.ACTIVATED && account.getStatus() != AccountStatus.CREATED){
                throw new IllegalArgumentException(account.getStatus().toString());
            }
            card.setStatus(CardStatus.ASSIGNED);
        }

        @Override
        public void activate(Card card) {
            log.warn("created card cannot be activated before assign");
            throw new IllegalArgumentException(card.getStatus().toString());
        }

        @Override
        public void deactivate(Card card) {
            log.warn("created card cannot be deactivated before activate");
            throw new IllegalArgumentException(card.getStatus().toString());
        }
    },
    ASSIGNED(1, "Assigned") {
        @Override
        public void assign(Card card, Account account) {
            log.warn("assigned card cannot be assign again");
            throw new IllegalArgumentException(card.getStatus().toString());
        }

        @Override
        public void activate(Card card) {
            card.setStatus(CardStatus.ACTIVATED);
        }

        @Override
        public void deactivate(Card card) {
            log.warn("assign card cannot be deactivated");
            throw new IllegalArgumentException(card.getStatus().toString());
        }
    },
    ACTIVATED(2, "Activated") {
        @Override
        public void assign(Card card, Account account) {
            log.warn("activated card cannot be assign again");
            throw new IllegalArgumentException(card.getStatus().toString());
        }

        @Override
        public void activate(Card card) {
            log.warn("activated card cannot be activated again");
            throw new IllegalArgumentException(card.getStatus().toString());
        }

        @Override
        public void deactivate(Card card) {
            card.setStatus(CardStatus.DEACTIVATED);
        }
    },
    DEACTIVATED(3, "Deactivated") {
        @Override
        public void assign(Card card, Account account) {
            log.warn("deactivated card cannot be assign again");
            throw new IllegalArgumentException(card.getStatus().toString());
        }

        @Override
        public void activate(Card card) {
            log.warn("deactivated card cannot be activated again");
            throw new IllegalArgumentException(card.getStatus().toString());
        }

        @Override
        public void deactivate(Card card) {
            log.warn("deactivated card cannot be deactivated again");
            throw new IllegalArgumentException(card.getStatus().toString());
        }
    };

    public abstract void assign(Card card, Account account);
    public abstract void activate(Card card);
    public abstract void deactivate(Card card);

    private int code;
    private String desc;

    CardStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CardStatus fromCode(int code) {
        for (CardStatus status : CardStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid CardStatus code: " + code);
    }
}
