package cn.snow.emsp.accts.domain.model.valueobjects;

import cn.hutool.crypto.digest.MD5;
import lombok.Getter;

@Getter
public class Rfid {

    private final String uid;
    private final String visibleNumber;

    public Rfid(String uid, String visibleNumber) {
        this.uid = uid;
        this.visibleNumber = visibleNumber;
    }

    public String getEncryptUid() {
        return MD5.create().digestHex16(uid);
    }

    public String getMarkedVisibleNumber() {
        if (visibleNumber.length() <= 4) {
            return visibleNumber;
        }
        return "****" + visibleNumber.substring(4);
    }
}
