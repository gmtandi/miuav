package com.fimi.soul.biz.p103k;

import android.os.Message;
import com.fimi.soul.entity.Relation;
import com.fimi.soul.entity.User;
import com.xiaomi.account.openauth.XMAuthericationException;

/* renamed from: com.fimi.soul.biz.k.bc */
class bc implements Runnable {
    final /* synthetic */ ba f6157a;
    private int f6158b;
    private User f6159c;
    private int f6160d;
    private String f6161e;
    private String f6162f;
    private long f6163g;
    private long f6164h;
    private String f6165i;
    private String f6166j;

    public bc(ba baVar, int i, int i2, User user) {
        this.f6157a = baVar;
        this.f6158b = i;
        this.f6159c = user;
        this.f6160d = i2;
    }

    public bc(ba baVar, int i, int i2, User user, String str) {
        this.f6157a = baVar;
        this.f6158b = i;
        this.f6159c = user;
        this.f6160d = i2;
        this.f6161e = str;
    }

    public bc(ba baVar, int i, String str) {
        this.f6157a = baVar;
        this.f6158b = i;
        this.f6166j = str;
    }

    public bc(ba baVar, int i, String str, long j, long j2, String str2) {
        this.f6157a = baVar;
        this.f6158b = i;
        this.f6162f = str;
        this.f6163g = j;
        this.f6165i = str2;
        this.f6164h = j2;
    }

    public void run() {
        Message obtainMessage = this.f6157a.f6153x.obtainMessage();
        Object obj = null;
        if (this.f6158b == 0) {
            obj = this.f6157a.f6151v.m9018a(this.f6157a.f6155z);
        } else if (this.f6158b == 1) {
            obj = this.f6157a.f6151v.m9129a(this.f6159c, this.f6157a.f6155z);
        } else if (this.f6158b == 3) {
            try {
                obj = this.f6157a.f6151v.m9133b(this.f6157a.f6155z);
            } catch (XMAuthericationException e) {
                e.printStackTrace();
            }
        } else if (this.f6158b == 4) {
            obj = this.f6157a.f6151v.m9134b(this.f6159c, this.f6157a.f6155z);
        } else if (this.f6158b == 5) {
            obj = this.f6157a.f6151v.m9137c(this.f6159c, this.f6157a.f6155z);
        } else if (this.f6158b == 6) {
            obj = this.f6157a.f6151v.m9135b(this.f6159c.getUserID(), this.f6160d, this.f6157a.f6155z);
        } else if (this.f6158b == 16) {
            obj = this.f6157a.f6151v.m9138c(this.f6159c.getUserID(), this.f6160d, this.f6157a.f6155z);
        } else if (this.f6158b == 8) {
            obj = this.f6157a.f6151v.m9130a(this.f6159c.getNickName(), this.f6160d, this.f6157a.f6155z);
        } else if (this.f6158b == 7) {
            Relation relation = new Relation();
            relation.setUserID(this.f6159c.getUserID());
            relation.setRelationID(this.f6159c.getCountry());
            obj = this.f6157a.f6151v.m9127a(relation, this.f6157a.f6155z);
        } else if (this.f6158b == 9) {
            obj = this.f6157a.f6151v.m9140d(this.f6159c, this.f6157a.f6155z);
        } else if (this.f6158b == 10) {
            obj = this.f6157a.f6151v.m9142e(this.f6159c, this.f6157a.f6155z);
        } else if (this.f6158b == 11) {
            obj = this.f6157a.f6151v.m9143f(this.f6159c, this.f6157a.f6155z);
        } else if (this.f6158b == 12) {
            obj = this.f6157a.f6151v.m9128a(this.f6159c, this.f6160d, this.f6161e, this.f6157a.f6155z);
        } else if (this.f6158b == 13) {
            obj = this.f6157a.f6151v.m9144g(this.f6159c, this.f6157a.f6155z);
        } else if (this.f6158b == 14) {
            obj = this.f6157a.f6151v.m9136b(this.f6159c.getUserID(), this.f6159c.getDevice(), this.f6157a.f6155z);
        } else if (this.f6158b == 15) {
            obj = this.f6157a.f6151v.m9145h(this.f6159c, this.f6157a.f6155z);
        } else if (this.f6158b == 17) {
            obj = this.f6157a.f6151v.m9131a(this.f6162f, this.f6163g, this.f6164h, this.f6165i, this.f6157a.f6155z);
        } else if (this.f6158b == 18) {
            obj = this.f6157a.f6151v.m9139c(this.f6166j, this.f6157a.f6155z);
        } else if (this.f6158b == 19) {
            obj = this.f6157a.f6151v.m9141d(this.f6166j, this.f6157a.f6155z);
        }
        obtainMessage.what = this.f6158b;
        obtainMessage.obj = obj;
        this.f6157a.f6153x.sendMessage(obtainMessage);
    }
}
