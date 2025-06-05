package cn.snow.emsp.accts.service;

import cn.snow.emsp.accts.domain.model.Account;
import cn.snow.emsp.accts.domain.model.Card;
import cn.snow.emsp.accts.domain.model.valueobjects.AccountEmail;
import cn.snow.emsp.accts.domain.model.valueobjects.CardId;
import cn.snow.emsp.accts.domain.model.valueobjects.ContractId;
import cn.snow.emsp.accts.domain.model.valueobjects.Rfid;
import cn.snow.emsp.accts.domain.service.repository.AccountRepository;
import cn.snow.emsp.accts.persistence.mapper.AccountMapper;
import cn.snow.emsp.accts.persistence.mapper.CardMapper;
import cn.snow.emsp.accts.persistence.mapper.PageAccountMapper;
import cn.snow.emsp.accts.persistence.model.DbAccount;
import cn.snow.emsp.accts.persistence.model.DbAccountCard;
import cn.snow.emsp.accts.persistence.model.DbCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountMapper accountMapper;
    private final PageAccountMapper pageAccountMapper;
    private final CardMapper cardMapper;

    @Override
    public Account findByEmail(String email) {
        DbAccount dbAccount = accountMapper.selectByEmail(email);
        if (Objects.isNull(dbAccount)) {
            return null;
        }
        List<DbCard> dbCards = cardMapper.selectByAccountId(dbAccount.getId());
        Account account = new Account(new AccountEmail(dbAccount.getEmail()), new ContractId(dbAccount.getContractId()));
        account.loadFromDb(dbAccount, dbCards);
        return account;
    }

    @Override
    public Account save(Account account) {
        Long accountId = account.getAccountId();
        if (accountId == null) {
            DbAccount dbAccount = new DbAccount();
            dbAccount.setEmail(account.getEmail().getValue());
            dbAccount.setContractId(account.getContractId().getValue());
            dbAccount.setCreateAt(account.getCreatedAt());
            dbAccount.setLastUpdated(account.getLastUpdated());
            dbAccount.setStatus((short) account.getStatus().getCode());
            accountMapper.insert(dbAccount);
            account.setAccountId(dbAccount.getId());
            return account;
        }
        Integer version = account.getVersion();
        if (version == null) {
            throw new IllegalArgumentException("version must not be null when update account");
        }
        DbAccount dbAccount = new DbAccount();
        dbAccount.setId(accountId);
        dbAccount.setLastUpdated(account.getLastUpdated());
        dbAccount.setStatus((short) account.getStatus().getCode());
        int rows = accountMapper.updateStatus(dbAccount, version);
        if (rows == 0) {
            throw new IllegalArgumentException("version mismatch when update account");
        }
        return account;
    }

    @Override
    public Card save(Card card) {
        Long cardId = Optional.ofNullable(card).map(Card::getCardId).map(CardId::getId).orElseThrow(() -> new IllegalArgumentException("cardId must not be null when update card"));
        if (cardId == null) {
            DbCard dbCard = new DbCard();
            dbCard.setRfidUid(card.getRfid().getUid());
            dbCard.setRfidVisibleNumber(card.getRfid().getVisibleNumber());
            dbCard.setCreateAt(card.getCreatedAt());
            dbCard.setLastUpdated(card.getLastUpdated());
            dbCard.setStatus((short) card.getStatus().getCode());
            dbCard.setAccountId(card.getAccountEmail().getId());
            dbCard.setContractId(card.getContractId().getValue());
            dbCard.setAccountEmail(card.getAccountEmail().getValue());
            cardMapper.insert(dbCard);
            card.setCardId(new CardId(dbCard.getId()));
            return card;
        }
        Integer version = card.getVersion();
        if (version == null) {
            throw new IllegalArgumentException("version must not be null when update card");
        }
        DbCard dbCard = new DbCard();
        dbCard.setId(cardId);
        dbCard.setLastUpdated(card.getLastUpdated());
        dbCard.setAccountEmail(card.getAccountEmail().getValue());
        dbCard.setContractId(card.getContractId().getValue());
        dbCard.setAccountId(card.getAccountEmail().getId());
        dbCard.setStatus((short) card.getStatus().getCode());
        int rows = cardMapper.updateCard(dbCard, version);
        if (rows == 0) {
            throw new IllegalArgumentException("version mismatch when update card");
        }
        return card;
    }

    @Override
    public Page<DbAccountCard> findByLastUpdatedAfter(LocalDateTime dateTimeAfter, int page, int size) {
        int totalCnt = pageAccountMapper.countPageAccountCard(dateTimeAfter);
        int startRowNum = (page - 1) + size;
        if (startRowNum >= totalCnt) {
            return Page.empty();
        }
        List<DbAccountCard> l = pageAccountMapper.selectPageAccountCard(dateTimeAfter, startRowNum, size);
        l = Optional.ofNullable(l).orElse(List.of());
        return new PageImpl<>(l, org.springframework.data.domain.PageRequest.of(page, size), totalCnt);
    }

    @Override
    public Card findByCardId(String cardId) {
        DbCard dbCard = cardMapper.selectByCardId(Long.valueOf(cardId));
        if (Objects.isNull(dbCard)) {
            return null;
        }
        Card card = new Card(new Rfid(dbCard.getRfidUid(), dbCard.getRfidVisibleNumber()));
        card.loadFromDb(dbCard);
        return card;
    }
}
