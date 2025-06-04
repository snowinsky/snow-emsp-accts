package cn.snow.emsp.accts.domain.model.valueobjects;

import lombok.Getter;
import lombok.ToString;

import java.util.regex.Pattern;

@ToString
public class ContractId {

    @Getter
    private final String value;

    public ContractId(String value) {
        if (!isValidInput(value)) {
            throw new IllegalArgumentException("EMAID format error");
        }
        this.value = value;
    }

    public String getNormalizedValue() {
        if (isNormalized(value)) {
            return value;
        }
        return normalize(value);
    }


    // 允许带分隔符和校验位的输入格式（兼容 ISO 15118 柔性规则）
    private static final String FLEXIBLE_REGEX = "^[a-z]{2}(-?)[\\da-z]{3}(-?)[\\da-z]{9}((-?)[\\da-z])?$";
    private static final Pattern FLEXIBLE_PATTERN = Pattern.compile(FLEXIBLE_REGEX, Pattern.CASE_INSENSITIVE);

    // Hubject 规范化后的目标格式（14字符无分隔符）
    private static final String NORMALIZED_REGEX = "^[A-Z]{2}[A-Z0-9]{3}[A-Z0-9]{9}$";
    private static final Pattern NORMALIZED_PATTERN = Pattern.compile(NORMALIZED_REGEX);

    // 校验输入是否符合柔性规则
    public static boolean isValidInput(String emaid) {
        return emaid != null && FLEXIBLE_PATTERN.matcher(emaid).matches();
    }

    // 校验是否为规范化格式
    public static boolean isNormalized(String emaid) {
        return emaid != null && NORMALIZED_PATTERN.matcher(emaid).matches();
    }

    // 规范化操作：移除分隔符、删除校验位、转大写
    public static String normalize(String emaid) {
        if (!isValidInput(emaid)) {
            throw new IllegalArgumentException("无效的 EMAID 格式");
        }
        String removedHyphens = emaid.replace("-", "").toUpperCase();
        // 若长度=15（含校验位），删除最后一位
        return (removedHyphens.length() == 15) ? removedHyphens.substring(0, 14) : removedHyphens;
    }
}
