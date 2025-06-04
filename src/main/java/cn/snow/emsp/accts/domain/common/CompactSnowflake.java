package cn.snow.emsp.accts.domain.common;

import java.util.concurrent.atomic.AtomicLong;

public class CompactSnowflake {

    // 起始时间戳（2020-01-01）
    private static final long START_STAMP = 1577836800000L;
    // 时间戳有效位（30位≈34年范围）
    private static final int TIMESTAMP_BITS = 30;
    // 工作节点ID有效位（5位=32节点）
    private static final int WORKER_BITS = 5;
    // 序列号有效位（10位=1024序列）
    private static final int SEQUENCE_BITS = 10;
    private static final String BASE36_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final long workerId;
    private final AtomicLong lastTimestamp = new AtomicLong(-1);
    private final AtomicLong sequence = new AtomicLong(0);

    public CompactSnowflake(long workerId) {
        if (workerId < 0 || workerId > (1 << WORKER_BITS) - 1) {
            throw new IllegalArgumentException("Worker ID 必须在0-" + ((1 << WORKER_BITS) - 1) + "之间");
        }
        this.workerId = workerId;
    }

    public synchronized String nextId() {
        long currentTimestamp = System.currentTimeMillis() - START_STAMP;

        // 处理时钟回拨
        if (currentTimestamp < lastTimestamp.get()) {
            throw new RuntimeException("系统时钟回拨异常");
        }

        // 相同毫秒内增加序列号
        if (currentTimestamp == lastTimestamp.get()) {
            sequence.set((sequence.get() + 1) & ((1 << SEQUENCE_BITS) - 1));
            if (sequence.get() == 0) {
                currentTimestamp = waitNextMillis(lastTimestamp.get());
            }
        } else {
            sequence.set(0);
        }

        lastTimestamp.set(currentTimestamp);

        // 组合ID：30位时间戳 + 5位工作节点 + 10位序列号
        long id = (currentTimestamp << (WORKER_BITS + SEQUENCE_BITS))
                | (workerId << SEQUENCE_BITS)
                | sequence.get();

        // 转换为9位Base36
        return toBase36(id, 9);
    }

    private long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis() - START_STAMP;
        while (timestamp <= lastTimestamp) {
            Thread.yield();
            timestamp = System.currentTimeMillis() - START_STAMP;
        }
        return timestamp;
    }

    private String toBase36(long value, int length) {
        char[] chars = new char[length];
        long radix = 36;

        for (int i = length - 1; i >= 0; i--) {
            int remainder = (int) (value % radix);
            value = value / radix;
            chars[i] = BASE36_CHARS.charAt(remainder);
        }

        // 添加校验位（可选）
        int checksum = 0;
        for (char c : chars) {
            checksum = (checksum + BASE36_CHARS.indexOf(c)) % 36;
        }

        return new String(chars) + BASE36_CHARS.charAt(checksum % 36);
    }
}
