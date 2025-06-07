package cn.snow.emsp.accts.domain.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardStatusTest {

    @Test
    void fromCode() {
        for (CardStatus status : CardStatus.values()) {
            assert null != status.getDesc();
            assertEquals(status, CardStatus.fromCode(status.getCode()));
            assertThrows(IllegalArgumentException.class, () -> CardStatus.fromCode(status.getCode() + 1000));
        }
    }
}