package cn.snow.emsp.accts.domain.service.events;

import cn.snow.emsp.accts.domain.model.Account;

public class AccountActiveEvent {

    private final Account account;

    public AccountActiveEvent(Account account){
        this.account = account;
    }

}
