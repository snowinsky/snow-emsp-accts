package cn.snow.emsp.accts.domain.service;

import cn.snow.emsp.accts.domain.model.Account;
import cn.snow.emsp.accts.domain.model.Card;
import cn.snow.emsp.accts.domain.model.enums.AccountStatus;

import java.util.List;

public interface EMSPAccountCardService {

    Account createAccount(String email, String contractId);
    void changeStatusOfAccount(String email, AccountStatus status);
    Card createCard(Card card);
    void changeStatusOfCard(String cardId, AccountStatus status);
    void assignCardToAccount(String cardId, String email);
    List<Account> getAccountsByLastUpdated(int page, int pageSize);
}
