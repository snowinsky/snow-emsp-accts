package cn.snow.emsp.accts.domain.model;

import cn.snow.emsp.accts.domain.model.enums.CardStatus;
import cn.snow.emsp.accts.domain.model.valueobjects.AccountEmail;
import cn.snow.emsp.accts.domain.model.valueobjects.ContractId;
import cn.snow.emsp.accts.domain.model.valueobjects.Rfid;

class CardTest {

    @org.junit.jupiter.api.Test
    void testCardCreate() {
        Card card = new Card(new Rfid("asdf", "asdfa"));
        assert card.getStatus() == CardStatus.CREATED;
    }

    @org.junit.jupiter.api.Test
    void testCardActive() {
        Card card = new Card(new Rfid("asdf", "asdfa"));
        Account account = new Account(new AccountEmail("asdf@sa.com"), new ContractId("CNABC123456789"));
        card.assignTo(account);
        card.activate();
        assert card.getStatus() == CardStatus.ACTIVATED;
    }

    @org.junit.jupiter.api.Test
    void testCardDeactive() {
        Card card = new Card(new Rfid("asdf", "asdfa"));
        Account account = new Account(new AccountEmail("asdf@few.com"), new ContractId("CNABC123456789"));
        card.assignTo(account);
        card.activate();
        card.deactivate();
        assert card.getStatus() == CardStatus.DEACTIVATED;
    }

}