package com.tencent.stat.p136a;

import android.content.Context;
import com.tencent.stat.StatGameUser;
import com.tencent.stat.common.C2418k;
import org.json.JSONObject;

/* renamed from: com.tencent.stat.a.g */
public class C2400g extends C2394e {
    private StatGameUser f12281a;

    public C2400g(Context context, int i, StatGameUser statGameUser) {
        super(context, i);
        this.f12281a = null;
        this.f12281a = statGameUser.clone();
    }

    public C2399f m13951a() {
        return C2399f.MTA_GAME_USER;
    }

    public boolean m13952a(JSONObject jSONObject) {
        if (this.f12281a == null) {
            return false;
        }
        C2418k.m14014a(jSONObject, "wod", this.f12281a.getWorldName());
        C2418k.m14014a(jSONObject, "gid", this.f12281a.getAccount());
        C2418k.m14014a(jSONObject, "lev", this.f12281a.getLevel());
        return true;
    }
}
