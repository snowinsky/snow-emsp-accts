package cn.snow.emsp.accts.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PageAccountCard implements Serializable {
    private Long accountId;
    private String markedEmail;
    private String contractId;
    private LocalDateTime lastUpdated;
    private String accountStatus;
    private Long cardId;
    private String cardStatus;
    private String markedRfidVisibleNumber;

}
