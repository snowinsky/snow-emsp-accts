package cn.snow.emsp.accts.domain.model;

import cn.snow.emsp.accts.domain.model.enums.AccountStatus;
import cn.snow.emsp.accts.domain.model.enums.CardStatus;
import cn.snow.emsp.accts.domain.model.valueobjects.AccountEmail;
import cn.snow.emsp.accts.domain.model.valueobjects.ContractId;
import cn.snow.emsp.accts.domain.model.valueobjects.Rfid;
import cn.snow.emsp.accts.domain.service.EMSPContractIdService;
import cn.snow.emsp.accts.domain.service.EMSPContractIdServiceImpl;

class AccountTest {

    @org.junit.jupiter.api.Test
    void testCreateAccountThenActiveThenDeactive() {
        String email = "1@a.com";
        AccountEmail accountEmail = new AccountEmail(email);
        EMSPContractIdService contractIdService = new EMSPContractIdServiceImpl("CN", "ABC", 1);
        String contractIdValue = contractIdService.generateNormalizedContractId();
        ContractId contractId = new ContractId(contractIdValue);
        Account account = new Account(accountEmail, contractId);
        assert account.getEmail().getValue().equals(email);
        assert account.getContractId().getValue().equals(contractIdValue);
        assert account.getStatus().equals(AccountStatus.CREATED);
        assert account.getCreatedAt() != null;
        assert account.getLastUpdated() != null;
        assert account.getCards().isEmpty();
        account.activate();
        assert account.getStatus().equals(AccountStatus.ACTIVATED);
        account.deactivate();
        assert account.getStatus().equals(AccountStatus.DEACTIVATED);
    }

    @org.junit.jupiter.api.Test
    void testCreateAccountThenActiveThenAssignCard() {
        String email = "1@a.com";
        AccountEmail accountEmail = new AccountEmail(email);
        EMSPContractIdService contractIdService = new EMSPContractIdServiceImpl("CN", "ABC", 1);
        String contractIdValue = contractIdService.generateNormalizedContractId();
        ContractId contractId = new ContractId(contractIdValue);
        Account account = new Account(accountEmail, contractId);

        Card card = new Card(new Rfid("123", "234234233"));
        try {
            account.assignCard(card);
        } catch (IllegalStateException e) {
            assert e.getMessage().equals("Account must be activated");
        }
        account.activate();
        try {
            account.assignCard(null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Card contract ID is null");
        }
        account.assignCard(card);
        assert account.getCards().size() == 1;
        assert account.getCards().contains(card);
        assert card.getAccountEmail().equals(account.getEmail());
    }

    @org.junit.jupiter.api.Test
    void testCreateAccountThenActiveThenAssignCardThenDeactive() {
        String email = "1@a.com";
        AccountEmail accountEmail = new AccountEmail(email);
        EMSPContractIdService contractIdService = new EMSPContractIdServiceImpl("CN", "ABC", 1);
        String contractIdValue = contractIdService.generateNormalizedContractId();
        ContractId contractId = new ContractId(contractIdValue);
        Account account = new Account(accountEmail, contractId);
        Card card = new Card(new Rfid("123", "234234233"));
        account.activate();
        account.assignCard(card);
        card.activate();
        account.deactivate();
        assert account.getStatus().equals(AccountStatus.DEACTIVATED);
        for (Card accountCard : account.getCards()) {
            assert accountCard.getStatus().equals(CardStatus.DEACTIVATED);
        }
    }

}