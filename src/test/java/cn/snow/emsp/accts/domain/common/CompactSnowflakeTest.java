package cn.snow.emsp.accts.domain.common;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class CompactSnowflakeTest {

    @Test
    void nextId() throws InterruptedException {
        Set<String> l = new HashSet<>(100);
        CompactSnowflake compactSnowflake = new CompactSnowflake(1);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                l.add(compactSnowflake.nextId());
            }).start();
        }
        Thread.sleep(1000);
        assert l.size() == 100;
    }
}