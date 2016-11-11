package com.tencent.mm.sdk.platformtools;

public class CharSequences {

    /* renamed from: com.tencent.mm.sdk.platformtools.CharSequences.1 */
    final class C22681 implements CharSequence {
        final /* synthetic */ byte[] f11799U;

        C22681(byte[] bArr) {
            this.f11799U = bArr;
        }

        public final char charAt(int i) {
            return (char) this.f11799U[i];
        }

        public final int length() {
            return this.f11799U.length;
        }

        public final CharSequence subSequence(int i, int i2) {
            return CharSequences.forAsciiBytes(this.f11799U, i, i2);
        }

        public final String toString() {
            return new String(this.f11799U);
        }
    }

    /* renamed from: com.tencent.mm.sdk.platformtools.CharSequences.2 */
    final class C22692 implements CharSequence {
        final /* synthetic */ byte[] f11800U;
        final /* synthetic */ int f11801V;
        final /* synthetic */ int f11802W;

        C22692(byte[] bArr, int i, int i2) {
            this.f11800U = bArr;
            this.f11801V = i;
            this.f11802W = i2;
        }

        public final char charAt(int i) {
            return (char) this.f11800U[this.f11801V + i];
        }

        public final int length() {
            return this.f11802W - this.f11801V;
        }

        public final CharSequence subSequence(int i, int i2) {
            int i3 = i - this.f11801V;
            int i4 = i2 - this.f11801V;
            CharSequences.m13531a(i3, i4, length());
            return CharSequences.forAsciiBytes(this.f11800U, i3, i4);
        }

        public final String toString() {
            return new String(this.f11800U, this.f11801V, length());
        }
    }

    static void m13531a(int i, int i2, int i3) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 > i3) {
            throw new IndexOutOfBoundsException();
        } else if (i > i2) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static int compareToIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        int i = 0;
        int length = charSequence.length();
        int length2 = charSequence2.length();
        int i2 = length < length2 ? length : length2;
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i3 + 1;
            char toLowerCase = Character.toLowerCase(charSequence.charAt(i3));
            i3 = i + 1;
            i = toLowerCase - Character.toLowerCase(charSequence2.charAt(i));
            if (i != 0) {
                return i;
            }
            i = i3;
            i3 = i4;
        }
        return length - length2;
    }

    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence.length() != charSequence2.length()) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static CharSequence forAsciiBytes(byte[] bArr) {
        return new C22681(bArr);
    }

    public static CharSequence forAsciiBytes(byte[] bArr, int i, int i2) {
        m13531a(i, i2, bArr.length);
        return new C22692(bArr, i, i2);
    }
}
