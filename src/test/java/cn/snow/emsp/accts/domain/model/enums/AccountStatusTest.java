package cn.snow.emsp.accts.domain.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountStatusTest {

    @Test
    void fromCode() {
        AccountStatus accountStatus = AccountStatus.fromCode(0);
        assertEquals(accountStatus, AccountStatus.CREATED);
        accountStatus = AccountStatus.fromCode(1);
        assertEquals(accountStatus, AccountStatus.ACTIVATED);
        accountStatus = AccountStatus.fromCode(2);
        assertEquals(accountStatus, AccountStatus.DEACTIVATED);
        assertThrows(IllegalArgumentException.class, () -> AccountStatus.fromCode(3));

        assert null != accountStatus.getDesc();

    }

}