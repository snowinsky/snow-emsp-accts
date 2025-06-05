package cn.snow.emsp.accts.service.impl;

import cn.snow.emsp.accts.domain.model.Account;
import cn.snow.emsp.accts.domain.model.Card;
import cn.snow.emsp.accts.domain.model.enums.AccountStatus;
import cn.snow.emsp.accts.domain.model.enums.CardStatus;
import cn.snow.emsp.accts.domain.model.valueobjects.AccountEmail;
import cn.snow.emsp.accts.domain.model.valueobjects.ContractId;
import cn.snow.emsp.accts.domain.model.valueobjects.Rfid;
import cn.snow.emsp.accts.domain.service.EMSPContractIdService;
import cn.snow.emsp.accts.domain.service.events.*;
import cn.snow.emsp.accts.domain.service.events.eventbus.AccountCardEventPublisher;
import cn.snow.emsp.accts.domain.service.repository.AccountRepository;
import cn.snow.emsp.accts.persistence.model.DbAccountCard;
import cn.snow.emsp.accts.service.AccountCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountCardServiceImpl implements AccountCardService {

    private final EMSPContractIdService emspContractIdService;
    private final AccountRepository accountRepository;
    private final AccountCardEventPublisher accountCardEventPublisher;

    @Override
    @Transactional
    public Account createAccount(String email) {
        Account account = new Account(new AccountEmail(email),
                new ContractId(emspContractIdService.generateContractId()));
        if (accountRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("account already exists by email: " + email);
        }
        accountRepository.save(account);
        accountCardEventPublisher.publishEvent(new AccountCreatedEvent(account));
        return account;
    }

    @Override
    @Transactional
    public Account changeStatusOfAccount(String email, AccountStatus status) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new IllegalArgumentException("account not found by email: " + email);
        }
        switch (status) {
            case ACTIVATED:
                account.activate();
                accountRepository.save(account);
                accountCardEventPublisher.publishEvent(new AccountActiveEvent(account));
                break;
            case DEACTIVATED:
                account.deactivate();
                accountRepository.save(account);
                accountCardEventPublisher.publishEvent(new AccountDeactiveEvent(account));
                break;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }
        return account;
    }

    @Override
    @Transactional
    public Card createCard(String rfidUid, String rfidVisibleNumber) {
        Card card = new Card(new Rfid(rfidUid, rfidVisibleNumber));
        if (accountRepository.findByRfidVisibleNumber(rfidVisibleNumber) != null) {
            throw new IllegalArgumentException("card already exists by rfidVisibleNumber: " + card.getRfid().getVisibleNumber());
        }
        accountRepository.save(card);
        accountCardEventPublisher.publishEvent(new CardCreateEvent(card));
        return card;
    }

    @Override
    @Transactional
    public Card changeStatusOfCard(String cardId, CardStatus status) {
        Card card = accountRepository.findByCardId(cardId);
        if (card == null) {
            throw new IllegalArgumentException("card not found by cardId: " + cardId);
        }
        switch (status) {
            case ACTIVATED:
                card.activate();
                accountRepository.save(card);
                break;
            case DEACTIVATED:
                card.deactivate();
                accountRepository.save(card);
                break;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }
        return card;
    }

    @Override
    @Transactional
    public Account assignCardToAccount(String cardId, String email) {
        Card card = accountRepository.findByCardId(cardId);
        Account account = accountRepository.findByEmail(email);
        if (card == null) {
            throw new IllegalArgumentException("card not found by cardId: " + cardId);
        }
        if (account == null) {
            throw new IllegalArgumentException("account not found by email: " + email);
        }
        account.assignCard(card);
        accountRepository.save(card);
        accountCardEventPublisher.publishEvent(new CardAssignEvent(card));
        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DbAccountCard> getAccountsByLastUpdated(LocalDateTime dateTimeAfter, int page, int pageSize) {
        return accountRepository.findByLastUpdatedAfter(dateTimeAfter, page, pageSize);
    }
}
