package cn.snow.emsp.accts.domain.common;

import lombok.extern.slf4j.Slf4j;

import static cn.snow.emsp.accts.domain.common.EMAIDValidator.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class EMAIDValidatorTest {

    @org.junit.jupiter.api.Test
    void testValidInput() {
        String[] testEmails = {
                "DE-123-4567890",      // ✅ 有效输入 -> 规范化: DE1234567890
                "DE-123-4567890-1",      // ✅ 有效输入 -> 规范化: DE1234567890
                "DE-123-4567890-2",      // ✅ 有效输入 -> 规范化: DE1234567890
                "DE-123-012345678-2",      // ✅ 有效输入 -> 规范化: DE1234567890
                "de-123-012345678-2",      // ✅ 有效输入 -> 规范化: DE1234567890
                "DE-123-012345678",      // ✅ 有效输入 -> 规范化: DE1234567890
                "de-123-4567890",      // ✅ 有效输入 -> 规范化: DE1234567890
                "frx9j3k7xyz12345",    // ✅ 有效输入 -> 规范化: FRX9J3K7XYZ12345
                "CNTECH5EV-SERIAL123",  // ✅ 有效输入 -> 规范化: CNTECHEVSERIAL12
                "US12!45XXX",           // ❌ 无效（含非法字符）
                "DE123"                 // ❌ 无效（长度不足）
        };

        boolean[] expectedResults = {
                false, false, false, true, true, true, false, false, false, false, false
        };

        for (int i = 0; i < testEmails.length; i++) {
            String emaid = testEmails[i];
            boolean expected = expectedResults[i];
            assert expected == isValidInput(emaid);
            log.info("输入: " + emaid);
            if (isValidInput(emaid)) {
                String normalized = normalize(emaid);
                log.info("规范化结果: " + normalized);
                log.info("是否符合Hubject标准: " + isNormalized(normalized));
            } else {
                log.info("❌ 无效格式");
            }
            log.info("------");
        }
    }

}