package cn.snow.emsp.accts.api.vo;

import cn.snow.emsp.accts.domain.model.Card;
import cn.snow.emsp.accts.domain.model.valueobjects.AccountEmail;
import cn.snow.emsp.accts.domain.model.valueobjects.ContractId;
import lombok.Data;

import java.util.Optional;

@Data
public class CardResponse  implements java.io.Serializable{

    private Long cardId;
    private String cardMarkedVisibleNumber;
    private String status;
    private String contractId;
    private String accountEmail;


    public static CardResponse from(Card card) {
        CardResponse cardResponse = new CardResponse();
        cardResponse.setCardId(card.getCardId().getId());
        cardResponse.setCardMarkedVisibleNumber(card.getRfid().getMarkedVisibleNumber());
        cardResponse.setStatus(card.getStatus().getDesc());
        cardResponse.setContractId(Optional.ofNullable(card.getContractId()).map(ContractId::getNormalizedValue).orElse(""));
        cardResponse.setAccountEmail(Optional.ofNullable(card.getAccountEmail()).map(AccountEmail::getValue).orElse(""));
        return cardResponse;
    }
}
