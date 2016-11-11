package org.p004c.p198b;

import java.util.ArrayList;
import java.util.List;

/* renamed from: org.c.b.a */
public class C3393a extends AssertionError {
    private static final long serialVersionUID = 1;
    private final List<Integer> f15888a;
    private final String f15889b;

    public C3393a(String str, AssertionError assertionError, int i) {
        this.f15888a = new ArrayList();
        this.f15889b = str;
        initCause(assertionError);
        m18634a(i);
    }

    public void m18634a(int i) {
        this.f15888a.add(0, Integer.valueOf(i));
    }

    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f15889b != null) {
            stringBuilder.append(this.f15889b);
        }
        stringBuilder.append("arrays first differed at element ");
        for (Integer intValue : this.f15888a) {
            int intValue2 = intValue.intValue();
            stringBuilder.append("[");
            stringBuilder.append(intValue2);
            stringBuilder.append("]");
        }
        stringBuilder.append("; ");
        stringBuilder.append(getCause().getMessage());
        return stringBuilder.toString();
    }

    public String toString() {
        return getMessage();
    }
}
