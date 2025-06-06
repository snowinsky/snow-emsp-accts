package cn.snow.emsp.accts.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateCardRequest  implements java.io.Serializable{
    @NotNull(message = "rfidUid is empty")
    @Size(min = 5, max = 100, message = "rfidUid length must be 5-100")
    private String rfidUid;
    @NotNull(message = "rfidVisibleNumber is empty")
    @Size(min = 16, max = 18, message = "rfidVisibleNumber length must be 16-18")
    private String rfidVisibleNumber;
}
