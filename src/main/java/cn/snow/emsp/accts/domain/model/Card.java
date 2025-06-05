package cn.snow.emsp.accts.domain.model;

import cn.snow.emsp.accts.domain.model.enums.CardStatus;
import cn.snow.emsp.accts.domain.model.valueobjects.AccountEmail;
import cn.snow.emsp.accts.domain.model.valueobjects.CardId;
import cn.snow.emsp.accts.domain.model.valueobjects.ContractId;
import cn.snow.emsp.accts.domain.model.valueobjects.Rfid;
import cn.snow.emsp.accts.persistence.model.DbCard;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Getter
public class Card {
    private final Rfid rfid;
    private final LocalDateTime createdAt;
    @Setter
    private CardId cardId;
    private ContractId contractId;
    private AccountEmail accountEmail;
    private CardStatus status;
    private LocalDateTime lastUpdated;
    @Getter
    private Integer version;

    public Card(Rfid rfid) {
        this.rfid = rfid;
        this.status = CardStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
    }

    public void assignTo(Account account) {
        if (this.status != CardStatus.CREATED) {
            throw new IllegalStateException("Card must be in CREATED state");
        }
        this.contractId = account.getContractId();
        this.accountEmail = account.getEmail();
        this.status = CardStatus.ASSIGNED;
        this.lastUpdated = LocalDateTime.now();
    }

    public void activate() {
        if (this.status != CardStatus.ASSIGNED) {
            throw new IllegalStateException("Card must be assigned first");
        }
        this.status = CardStatus.ACTIVATED;
        this.lastUpdated = LocalDateTime.now();
    }

    public void deactivate() {
        if (this.status != CardStatus.ACTIVATED) {
            throw new IllegalStateException("Card must be activated");
        }
        this.status = CardStatus.DEACTIVATED;
        this.lastUpdated = LocalDateTime.now();
    }

    public Card loadFromDb(DbCard dbCard) {
        this.cardId = Optional.ofNullable(dbCard.getId()).map(CardId::new).orElse(null);
        this.contractId = Optional.ofNullable(dbCard.getContractId()).map(ContractId::new).orElse(null);
        this.accountEmail = Optional.ofNullable(dbCard.getAccountEmail()).map(a -> {
            AccountEmail accountEmail = new AccountEmail(a);
            accountEmail.setId(dbCard.getAccountId());
            return accountEmail;
        }).orElse(null);
        this.status = CardStatus.fromCode(dbCard.getStatus());
        this.lastUpdated = dbCard.getLastUpdated();
        this.version = dbCard.getVers();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(rfid, card.rfid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rfid);
    }
}
