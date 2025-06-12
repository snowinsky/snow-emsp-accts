package cn.snow.emsp.accts.domain.model.enums;

import cn.snow.emsp.accts.domain.model.Account;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum AccountStatus {
    CREATED(0, "Created") {
        @Override
        public void activate(Account account) {
            account.setStatus(ACTIVATED);
        }

        @Override
        public void deactivated(Account account) {
            log.warn("created account cannot be deactived");
            throw new IllegalArgumentException(account.getStatus().toString());
        }
    },
    ACTIVATED(1, "Activated") {
        @Override
        public void activate(Account account) {
            log.warn("activated account cannot be activated again");
            throw new IllegalArgumentException(account.getStatus().toString());
        }

        @Override
        public void deactivated(Account account) {
            account.setStatus(DEACTIVATED);
        }
    },
    DEACTIVATED(2, "Deactivated") {
        @Override
        public void activate(Account account) {
            log.warn("deactivated account cannot be activated again");
            throw new IllegalArgumentException(account.getStatus().toString());
        }

        @Override
        public void deactivated(Account account) {
            log.warn("deactivated account cannot be deactivated again");
            throw new IllegalArgumentException(account.getStatus().toString());
        }
    };

    public abstract void activate(Account account);

    public abstract void deactivated(Account account);

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