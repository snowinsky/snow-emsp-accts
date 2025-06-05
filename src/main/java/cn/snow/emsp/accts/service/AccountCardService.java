package cn.snow.emsp.accts.service;

import cn.snow.emsp.accts.domain.model.Account;
import cn.snow.emsp.accts.domain.model.Card;
import cn.snow.emsp.accts.domain.model.enums.AccountStatus;
import cn.snow.emsp.accts.domain.model.enums.CardStatus;
import cn.snow.emsp.accts.persistence.model.DbAccountCard;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface AccountCardService {

    Account createAccount(String email);

    Account changeStatusOfAccount(String email, AccountStatus status);

    Card createCard(String rfidUid, String rfidVisibleNumber);

    Card changeStatusOfCard(String cardId, CardStatus status);

    Account assignCardToAccount(String cardId, String email);

    Page<DbAccountCard> getAccountsByLastUpdated(LocalDateTime dateTimeAfter, int page, int pageSize);
}
