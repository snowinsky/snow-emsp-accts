package cn.snow.emsp.accts.domain.model.valueobjects;

import lombok.Getter;

@Getter
public class CardId {

    private final Long id;

    public CardId(Long id) {
        this.id = id;
    }

}
