package cn.snow.emsp.accts.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CreateAccountRequest implements java.io.Serializable{

    @NotNull(message = "email is empty")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "email format error")
    private String email;

}
