package cn.snow.emsp.accts.persistence.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DbAccount implements java.io.Serializable {
    private Long id;
    private Integer vers;
    private String email;
    private String contractId;
    private Short status;
    private LocalDateTime createAt;
    private LocalDateTime lastUpdated;
}
