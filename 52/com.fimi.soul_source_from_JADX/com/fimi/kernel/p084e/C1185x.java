package com.fimi.kernel.p084e;

import android.util.SparseArray;
import android.view.View;

/* renamed from: com.fimi.kernel.e.x */
public class C1185x {
    public static <T extends View> T m8294a(View view, int i) {
        SparseArray sparseArray;
        SparseArray sparseArray2 = (SparseArray) view.getTag();
        if (sparseArray2 == null) {
            sparseArray2 = new SparseArray();
            view.setTag(sparseArray2);
            sparseArray = sparseArray2;
        } else {
            sparseArray = sparseArray2;
        }
        View view2 = (View) sparseArray.get(i);
        if (view2 != null) {
            return view2;
        }
        T findViewById = view.findViewById(i);
        sparseArray.put(i, findViewById);
        return findViewById;
    }
}
