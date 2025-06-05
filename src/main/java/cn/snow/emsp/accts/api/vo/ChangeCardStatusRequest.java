package cn.snow.emsp.accts.api.vo;

import lombok.Data;
import org.checkerframework.common.value.qual.EnumVal;

import javax.validation.constraints.NotNull;

@Data
public class ChangeCardStatusRequest  implements java.io.Serializable{

    @NotNull(message = "status is empty")
    @EnumVal(value = {"ASSIGNED", "ACTIVATED", "DEACTIVATED"})
    private String status;
}
