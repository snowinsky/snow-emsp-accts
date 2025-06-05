package cn.snow.emsp.accts.persistence.model;

import cn.snow.emsp.accts.domain.model.enums.AccountStatus;
import cn.snow.emsp.accts.domain.model.enums.CardStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DbAccountCard {
    private Long accountId;
    private String email;
    private String contractId;
    private Short accountStatus;
    private LocalDateTime createAt;
    private LocalDateTime lastUpdated;

    private Long cardId;
    private String rfidUid;
    private String rfidVisibleNumber;
    private Short cardStatus;

    public String getMarkedEmail() {
        if (email == null || email.length() < 6) {
            return email;
        }
        return email.substring(0, 3) + "***" + email.substring(email.length() - 3);
    }

    public String getAccountStatusName(){
        if(accountStatus==null){
            return null;
        }
        return AccountStatus.fromCode(accountStatus).name();
    }

    public String getMarkedRfidVisibleNumber(){
        if(rfidVisibleNumber==null || rfidVisibleNumber.length()<6){
            return rfidVisibleNumber;
        }
        return rfidVisibleNumber.substring(0,3)+"***"+rfidVisibleNumber.substring(rfidVisibleNumber.length()-3);
    }

    public String getCardStatusName(){
        if(cardStatus==null){
            return null;
        }
        return CardStatus.fromCode(cardStatus).name();
    }
}
