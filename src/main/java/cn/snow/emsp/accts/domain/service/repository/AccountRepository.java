package cn.snow.emsp.accts.domain.service.repository;

import cn.snow.emsp.accts.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;

public interface AccountRepository {
    Account findByEmail(String email);
    Account save(Account account);
    Page<Account> findByLastUpdatedAfter(Instant timestamp, Pageable pageable);
}
