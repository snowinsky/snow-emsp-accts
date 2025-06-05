package cn.snow.emsp.accts.domain.model.enums;

import lombok.Getter;

@Getter
public enum AccountStatus {
    CREATED(0, "Created"),
    ACTIVATED(1, "Activated"),
    DEACTIVATED(2, "Deactivated");

    private int code;
    /**
     * -- GETTER --
     *
     * @return
     */
    private String desc;

    AccountStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AccountStatus fromCode(int code) {
        for (AccountStatus status : AccountStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid AccountStatus code: " + code);
    }


}