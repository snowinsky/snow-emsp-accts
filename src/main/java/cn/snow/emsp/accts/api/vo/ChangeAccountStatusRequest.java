package cn.snow.emsp.accts.api.vo;

import lombok.Data;
import org.checkerframework.common.value.qual.EnumVal;

import javax.validation.constraints.NotNull;

@Data
public class ChangeAccountStatusRequest implements java.io.Serializable {
    @NotNull(message = "account status is empty")
    @EnumVal(value = {"ACTIVATED", "DEACTIVATED"})
    private String accountStatus;
}
