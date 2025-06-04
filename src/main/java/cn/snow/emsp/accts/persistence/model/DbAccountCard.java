package cn.snow.emsp.accts.persistence.model;

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
}
