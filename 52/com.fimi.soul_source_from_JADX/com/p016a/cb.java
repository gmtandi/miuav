package com.p016a;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.cb */
public class cb {
    private ArrayList<cc> f725a;
    private ArrayList<ce> f726b;
    private short[][] f727c;
    private cd f728d;

    public cb() {
        this.f725a = new ArrayList();
        this.f726b = new ArrayList();
        this.f727c = (short[][]) Array.newInstance(Short.TYPE, new int[]{SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE});
        this.f728d = new cd(this);
    }

    public ArrayList<ce> m1278a(double d, double d2) {
        this.f728d.m1288a();
        ArrayList<ce> arrayList = new ArrayList();
        Iterator it = this.f728d.f735b.iterator();
        while (it.hasNext()) {
            ArrayList arrayList2 = (ArrayList) it.next();
            cc ccVar = new cc(this);
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                ccVar.m1284a((ce) this.f726b.get(((Integer) it2.next()).intValue()));
            }
            ccVar.m1283a();
            this.f725a.add(ccVar);
        }
        if (this.f725a.isEmpty()) {
            return null;
        }
        Collections.sort(this.f725a);
        if (!(d == 0.0d || d2 == 0.0d)) {
            ce ceVar = new ce(this, d2, d);
            for (int i = 0; i < this.f725a.size(); i++) {
                ce b = ((cc) this.f725a.get(i)).m1285b();
                double b2 = ceVar.m1291b(b);
                if (b.f740e > 0) {
                    if (b2 < 7000.0d && b2 > 2.0d) {
                        ((cc) this.f725a.get(i)).f732d = ((cc) this.f725a.get(i)).f732d * 5.0d;
                    }
                } else if (b2 < 10000.0d && b2 > 2.0d) {
                    ((cc) this.f725a.get(i)).f732d = ((cc) this.f725a.get(i)).f732d * 5.0d;
                }
            }
            Collections.sort(this.f725a);
        }
        for (int i2 = 0; i2 < this.f725a.size(); i2++) {
            if (arrayList.size() >= 5) {
                return arrayList;
            }
            arrayList.add(((cc) this.f725a.get(i2)).m1285b());
        }
        return arrayList;
    }

    public void m1279a(int i, String str) {
        int size = this.f726b.size();
        StringTokenizer stringTokenizer = new StringTokenizer(str, "\\|");
        if (stringTokenizer.countTokens() >= 3) {
            ArrayList arrayList = new ArrayList();
            while (stringTokenizer.hasMoreElements()) {
                arrayList.add(stringTokenizer.nextToken());
            }
            this.f726b.add(new ce(this, Double.parseDouble((String) arrayList.get(0)), Double.parseDouble((String) arrayList.get(1)), Double.valueOf(Double.parseDouble((String) arrayList.get(2))).intValue(), i));
            if (this.f726b.size() > SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                throw new Exception("Atomic Pos Larger Than MAXN 512!");
            }
            for (int i2 = 0; i2 < size; i2++) {
                for (int i3 = size; i3 < this.f726b.size(); i3++) {
                    if (((ce) this.f726b.get(i2)).m1289a((ce) this.f726b.get(i3))) {
                        this.f727c[i2][i3] = (short) 1;
                        this.f727c[i3][i2] = (short) 1;
                    }
                }
            }
            arrayList.clear();
        }
    }
}
