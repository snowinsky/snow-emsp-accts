package cn.snow.emsp.accts.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateAccountRequest implements java.io.Serializable{

    @NotNull(message = "email is empty")
    @Size(min = 5, max = 100, message = "email length must be between 5 and 100")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "email format error")
    private String email;

}
