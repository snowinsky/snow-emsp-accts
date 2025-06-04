package cn.snow.emsp.accts.persistence.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DbCard implements java.io.Serializable {

    private Long id;
    private Integer vers;
    private String rfidUid;
    private String rfidVisibleNumber;
    private String contractId;
    private Long accountId;
    private Short status;
    private LocalDateTime createAt;
    private LocalDateTime lastUpdated;
}
