package cn.snow.emsp.accts.domain.service.repository;

import cn.snow.emsp.accts.domain.model.Account;
import cn.snow.emsp.accts.domain.model.Card;
import cn.snow.emsp.accts.persistence.model.DbAccountCard;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface AccountRepository {
    Account findByEmail(String email);

    Account save(Account account);

    Card save(Card card);

    Page<DbAccountCard> findByLastUpdatedAfter(LocalDateTime localDateTime, int page, int size);

    Card findByCardId(String cardId);
}
