package com.fimi.soul.biz.update;

import com.fimi.soul.entity.FirmwareInfo;
import java.util.Comparator;

public class ae implements Comparator {
    public int compare(Object obj, Object obj2) {
        return String.valueOf(((FirmwareInfo) obj).getSort()).compareTo(String.valueOf(((FirmwareInfo) obj2).getSort()));
    }
}
