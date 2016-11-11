package com.p016a;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.a.cd */
class cd {
    final /* synthetic */ cb f734a;
    private ArrayList<ArrayList<Integer>> f735b;

    public cd(cb cbVar) {
        this.f734a = cbVar;
        this.f735b = null;
        this.f735b = new ArrayList();
    }

    private void m1287a(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        arrayList.add(Integer.valueOf(i2));
        this.f734a.f727c[i][i2] = (short) -1;
        this.f734a.f727c[i2][i] = (short) -1;
        ArrayList arrayList2 = new ArrayList();
        int i3 = 0;
        while (i3 < this.f734a.f726b.size()) {
            if (!(this.f734a.f727c[i][i3] == (short) 0 || this.f734a.f727c[i2][i3] == (short) 0)) {
                arrayList2.add(Integer.valueOf(i3));
            }
            i3++;
        }
        while (!arrayList2.isEmpty()) {
            int intValue = ((Integer) arrayList2.get(0)).intValue();
            arrayList2.remove(0);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Integer num = (Integer) it.next();
                this.f734a.f727c[intValue][num.intValue()] = (short) -1;
                this.f734a.f727c[num.intValue()][intValue] = (short) -1;
            }
            arrayList.add(Integer.valueOf(intValue));
            int i4 = 0;
            while (i4 < arrayList2.size()) {
                if (this.f734a.f727c[intValue][((Integer) arrayList2.get(i4)).intValue()] == (short) 0) {
                    arrayList2.remove(i4);
                    i3 = i4;
                } else {
                    i3 = i4 + 1;
                }
                i4 = i3;
            }
        }
        this.f735b.add(arrayList);
    }

    public void m1288a() {
        int i;
        int size = this.f734a.f726b.size();
        for (i = 0; i < size; i++) {
            for (int i2 = 0; i2 < size; i2++) {
                if (this.f734a.f727c[i][i2] > (short) 0) {
                    m1287a(i, i2);
                }
            }
        }
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = 1;
            i = 0;
            while (i < size) {
                if (this.f734a.f727c[i3][i] > (short) 0) {
                    throw new Exception("non visited edge");
                } else if (this.f734a.f727c[i3][i] < (short) 0) {
                    obj = null;
                    break;
                } else {
                    i++;
                }
            }
            if (obj != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(Integer.valueOf(i3));
                this.f735b.add(arrayList);
            }
        }
    }
}
