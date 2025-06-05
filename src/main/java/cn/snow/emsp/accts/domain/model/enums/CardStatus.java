package cn.snow.emsp.accts.domain.model.enums;

import lombok.Getter;

@Getter
public enum CardStatus {

    CREATED(0, "Created"),
    ASSIGNED(1, "Assigned"),
    ACTIVATED(2, "Activated"),
    DEACTIVATED(3, "Deactivated");

    private int code;
    private String desc;

    CardStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CardStatus fromCode(int code) {
        for (CardStatus status : CardStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid CardStatus code: " + code);
    }
}
