package cn.snow.emsp.accts.domain.model.valueobjects;

import cn.hutool.core.util.IdUtil;
import lombok.Getter;

@Getter
public class CardId {

    private final Long id;

    public CardId() {
        this.id = IdUtil.getSnowflakeNextId();
    }

}
