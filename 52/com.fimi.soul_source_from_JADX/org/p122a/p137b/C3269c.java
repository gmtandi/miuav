package org.p122a.p137b;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: org.a.b.c */
public final class C3269c {
    private static final Comparator f15779a;

    static {
        f15779a = new C3271e();
    }

    public static int m18086a(byte b, byte b2) {
        return b < b2 ? -1 : b2 < b ? 1 : 0;
    }

    public static int m18087a(int i, int i2) {
        return i < i2 ? -1 : i2 < i ? 1 : 0;
    }

    public static int m18088a(long j, long j2) {
        return j < j2 ? -1 : j2 < j ? 1 : 0;
    }

    public static int m18089a(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public static int m18090a(String str, String str2) {
        return str.compareTo(str2);
    }

    public static int m18091a(ByteBuffer byteBuffer, byte[] bArr, int i) {
        int remaining = byteBuffer.remaining();
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), bArr, i, remaining);
        return remaining;
    }

    public static int m18092a(List list, List list2) {
        int a = C3269c.m18087a(list.size(), list2.size());
        if (a != 0) {
            return a;
        }
        for (a = 0; a < list.size(); a++) {
            int compare = f15779a.compare(list.get(a), list2.get(a));
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    public static int m18093a(Map map, Map map2) {
        int a = C3269c.m18087a(map.size(), map2.size());
        if (a != 0) {
            return a;
        }
        SortedMap treeMap = new TreeMap(f15779a);
        treeMap.putAll(map);
        Iterator it = treeMap.entrySet().iterator();
        treeMap = new TreeMap(f15779a);
        treeMap.putAll(map2);
        Iterator it2 = treeMap.entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Entry entry = (Entry) it.next();
            Entry entry2 = (Entry) it2.next();
            int compare = f15779a.compare(entry.getKey(), entry2.getKey());
            if (compare != 0) {
                return compare;
            }
            a = f15779a.compare(entry.getValue(), entry2.getValue());
            if (a != 0) {
                return a;
            }
        }
        return 0;
    }

    public static int m18094a(Set set, Set set2) {
        int a = C3269c.m18087a(set.size(), set2.size());
        if (a != 0) {
            return a;
        }
        SortedSet treeSet = new TreeSet(f15779a);
        treeSet.addAll(set);
        SortedSet treeSet2 = new TreeSet(f15779a);
        treeSet2.addAll(set2);
        Iterator it = treeSet.iterator();
        Iterator it2 = treeSet2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            a = f15779a.compare(it.next(), it2.next());
            if (a != 0) {
                return a;
            }
        }
        return 0;
    }

    public static int m18095a(boolean z, boolean z2) {
        return Boolean.valueOf(z).compareTo(Boolean.valueOf(z2));
    }

    public static int m18096a(byte[] bArr, byte[] bArr2) {
        int a = C3269c.m18087a(bArr.length, bArr2.length);
        if (a != 0) {
            return a;
        }
        for (a = 0; a < bArr.length; a++) {
            int a2 = C3269c.m18086a(bArr[a], bArr2[a]);
            if (a2 != 0) {
                return a2;
            }
        }
        return 0;
    }

    public static String m18097a(byte b) {
        return Integer.toHexString((b | Opcodes.ACC_NATIVE) & 511).toUpperCase().substring(1);
    }

    public static void m18098a(ByteBuffer byteBuffer, StringBuilder stringBuilder) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        int limit = byteBuffer.limit();
        int i = limit - arrayOffset > SmileConstants.TOKEN_PREFIX_TINY_UNICODE ? arrayOffset + SmileConstants.TOKEN_PREFIX_TINY_UNICODE : limit;
        for (int i2 = arrayOffset; i2 < i; i2++) {
            if (i2 > arrayOffset) {
                stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            stringBuilder.append(C3269c.m18097a(array[i2]));
        }
        if (limit != i) {
            stringBuilder.append("...");
        }
    }

    public static byte[] m18099a(ByteBuffer byteBuffer) {
        if (C3269c.m18100b(byteBuffer)) {
            return byteBuffer.array();
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        C3269c.m18091a(byteBuffer, bArr, 0);
        return bArr;
    }

    public static boolean m18100b(ByteBuffer byteBuffer) {
        return byteBuffer.hasArray() && byteBuffer.position() == 0 && byteBuffer.arrayOffset() == 0 && byteBuffer.remaining() == byteBuffer.capacity();
    }

    public static ByteBuffer m18101c(ByteBuffer byteBuffer) {
        return C3269c.m18100b(byteBuffer) ? byteBuffer : ByteBuffer.wrap(C3269c.m18099a(byteBuffer));
    }
}
