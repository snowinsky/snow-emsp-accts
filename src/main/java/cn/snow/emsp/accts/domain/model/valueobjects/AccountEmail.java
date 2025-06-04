package cn.snow.emsp.accts.domain.model.valueobjects;

import lombok.Getter;
import lombok.ToString;

import java.util.regex.Pattern;

@ToString
@Getter
public class AccountEmail {

    private final String value;

    public AccountEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = email;
    }

    public String getValue() {
        return value;
    }

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
