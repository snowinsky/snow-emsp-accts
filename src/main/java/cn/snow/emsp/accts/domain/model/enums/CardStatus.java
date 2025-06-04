package cn.snow.emsp.accts.domain.model.enums;

import lombok.Getter;

@Getter
public enum CardStatus {

    CREATED(0, "Created"),
    ASSIGNED(2, "Assigned"),
    ACTIVATED(1, "Activated"),
    DEACTIVATED(-1, "Deactivated");

    private int code;
    private String desc;

    CardStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
