package org.p122a.p123a.p167i.p174d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.i.d.aa */
public class aa {
    private static final int f15461a = 256;
    private final C3161z f15462b;

    aa(C3161z c3161z) {
        this.f15462b = c3161z;
    }

    private boolean m17656a(Reader reader, StringBuilder stringBuilder) {
        stringBuilder.setLength(0);
        boolean z = false;
        do {
            int read = reader.read();
            if (read != -1) {
                char c = (char) read;
                if (c != '\n') {
                    if (Character.isWhitespace(c)) {
                        z = true;
                    }
                    if (!z) {
                        stringBuilder.append(c);
                    }
                }
            }
            return read != -1;
        } while (stringBuilder.length() <= f15461a);
        throw new IOException("Line too long");
    }

    public void m17657a(Reader reader) {
        Collection arrayList = new ArrayList();
        Collection arrayList2 = new ArrayList();
        Reader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder(f15461a);
        int i = 1;
        while (i != 0) {
            boolean a = m17656a(bufferedReader, stringBuilder);
            Object stringBuilder2 = stringBuilder.toString();
            if (stringBuilder2.length() == 0) {
                i = a;
            } else if (stringBuilder2.startsWith("//")) {
                i = a;
            } else {
                if (stringBuilder2.startsWith(".")) {
                    stringBuilder2 = stringBuilder2.substring(1);
                }
                boolean startsWith = stringBuilder2.startsWith("!");
                if (startsWith) {
                    stringBuilder2 = stringBuilder2.substring(1);
                }
                if (startsWith) {
                    arrayList2.add(stringBuilder2);
                } else {
                    arrayList.add(stringBuilder2);
                }
                i = a;
            }
        }
        this.f15462b.m17691a(arrayList);
        this.f15462b.m17692b(arrayList2);
    }
}
