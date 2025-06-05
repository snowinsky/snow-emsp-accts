package cn.snow.emsp.accts.api.vo;

import cn.snow.emsp.accts.domain.model.Account;
import cn.snow.emsp.accts.domain.model.valueobjects.ContractId;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class AccountResponse implements Serializable {
    private String accountEmail;
    private String accountStatus;
    private String contractId;
    private Set<CardResponse> cards;

    public static AccountResponse from(Account account) {
        AccountResponse response = new AccountResponse();
        response.setAccountEmail(account.getEmail().getValue());
        response.setAccountStatus(account.getStatus().getDesc());
        response.setContractId(Optional.ofNullable(account.getContractId()).map(ContractId::getNormalizedValue).orElse(""));
        response.setCards(account.getCards().stream().map(CardResponse::from).collect(Collectors.toSet()));
        return response;
    }
}
