package cn.snow.emsp.accts.domain.model.enums;

import lombok.Getter;

@Getter
public enum AccountStatus {
    CREATED(0, "Created"),
    ACTIVATED(1, "Activated"),
    DEACTIVATED(-1, "Deactivated");

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

}