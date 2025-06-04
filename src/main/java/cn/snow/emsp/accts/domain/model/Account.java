package cn.snow.emsp.accts.domain.model;

import cn.snow.emsp.accts.domain.model.enums.AccountStatus;
import cn.snow.emsp.accts.domain.model.valueobjects.AccountEmail;
import cn.snow.emsp.accts.domain.model.valueobjects.ContractId;
import cn.snow.emsp.accts.domain.service.events.AccountActiveEvent;
import cn.snow.emsp.accts.domain.service.events.AccountDeactiveEvent;
import cn.snow.emsp.accts.domain.service.events.eventbus.AcctsEventListener;
import com.google.common.eventbus.EventBus;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
public class Account {

    private final AccountEmail email;
    private final ContractId contractId;
    private final LocalDateTime createdAt;
    private final Set<Card> cards = new HashSet<>();
    private final EventBus eventBus = AcctsEventListener.getEventBus();
    private AccountStatus status;
    private LocalDateTime lastUpdated;


    public Account(AccountEmail email, ContractId contractId) {
        this.email = email;
        this.contractId = contractId;
        this.status = AccountStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
    }

    public void activate() {
        if (this.status != AccountStatus.CREATED) {
            throw new IllegalStateException("Account must be in CREATED state");
        }
        this.status = AccountStatus.ACTIVATED;
        this.lastUpdated = LocalDateTime.now();
        this.eventBus.post(new AccountActiveEvent(this));
    }

    public void deactivate() {
        if (this.status != AccountStatus.ACTIVATED) {
            throw new IllegalStateException("Account must be activated");
        }
        for (Card card : cards) {
            card.deactivate();
        }
        this.status = AccountStatus.DEACTIVATED;
        this.lastUpdated = LocalDateTime.now();
        this.eventBus.post(new AccountDeactiveEvent(this));
    }

    public void assignCard(Card card) {
        if (this.status != AccountStatus.ACTIVATED) {
            throw new IllegalStateException("Account must be activated");
        }
        if (card == null) {
            throw new IllegalArgumentException("Card contract ID is null");
        }
        card.assignTo(this);
        cards.add(card);
        this.lastUpdated = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

}
