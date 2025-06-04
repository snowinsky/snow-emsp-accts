package cn.snow.emsp.accts.domain.service;

import cn.snow.emsp.accts.domain.model.Account;
import cn.snow.emsp.accts.domain.model.Card;
import cn.snow.emsp.accts.domain.model.enums.AccountStatus;
import cn.snow.emsp.accts.domain.service.repository.AccountRepository;

import java.util.List;

public class EMSPAccountCardServiceImpl implements EMSPAccountCardService {

    private EMSPContractIdService contractIdService;
    private AccountRepository accountRepository;

    public void setContractIdService(EMSPContractIdService contractIdService) {
        this.contractIdService = contractIdService;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(String email, String contractId) {
        if (contractIdService == null) {
            throw new IllegalStateException("ContractIdService is not set");
        }
        if (contractId == null) {
            contractId = contractIdService.generateNormalizedContractId();
        }
        return null;
    }

    @Override
    public void changeStatusOfAccount(String email, AccountStatus status) {

    }

    @Override
    public Card createCard(Card card) {
        return null;
    }

    @Override
    public void changeStatusOfCard(String cardId, AccountStatus status) {

    }

    @Override
    public void assignCardToAccount(String cardId, String email) {

    }

    @Override
    public List<Account> getAccountsByLastUpdated(int page, int pageSize) {
        return List.of();
    }
}
