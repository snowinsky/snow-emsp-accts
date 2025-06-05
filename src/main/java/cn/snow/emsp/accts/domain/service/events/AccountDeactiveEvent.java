package cn.snow.emsp.accts.domain.service.events;

import cn.snow.emsp.accts.domain.model.Account;

public class AccountDeactiveEvent {

    private final Account account;

    public AccountDeactiveEvent(Account account){
        this.account = account;
    }

    @Override
    public String toString() {
        return String.format("Account(email=%s) was deactivated on %s", account.getEmail(), account.getLastUpdated().toString() );
    }
}
