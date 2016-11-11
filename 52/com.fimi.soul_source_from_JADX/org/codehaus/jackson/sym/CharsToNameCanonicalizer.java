package org.codehaus.jackson.sym;

import java.util.Arrays;
import org.codehaus.jackson.util.InternCache;
import org.p122a.p123a.C2915a;

public final class CharsToNameCanonicalizer {
    protected static final int DEFAULT_TABLE_SIZE = 64;
    static final int MAX_ENTRIES_FOR_REUSE = 12000;
    protected static final int MAX_TABLE_SIZE = 65536;
    static final CharsToNameCanonicalizer sBootstrapSymbolTable;
    protected Bucket[] _buckets;
    protected final boolean _canonicalize;
    protected boolean _dirty;
    protected int _indexMask;
    protected final boolean _intern;
    protected CharsToNameCanonicalizer _parent;
    protected int _size;
    protected int _sizeThreshold;
    protected String[] _symbols;

    final class Bucket {
        private final String _symbol;
        private final Bucket mNext;

        public Bucket(String str, Bucket bucket) {
            this._symbol = str;
            this.mNext = bucket;
        }

        public String find(char[] cArr, int i, int i2) {
            String str = this._symbol;
            Bucket bucket = this.mNext;
            while (true) {
                if (str.length() == i2) {
                    int i3 = 0;
                    while (str.charAt(i3) == cArr[i + i3]) {
                        i3++;
                        if (i3 >= i2) {
                            break;
                        }
                    }
                    if (i3 == i2) {
                        return str;
                    }
                }
                if (bucket == null) {
                    return null;
                }
                str = bucket.getSymbol();
                bucket = bucket.getNext();
            }
        }

        public Bucket getNext() {
            return this.mNext;
        }

        public String getSymbol() {
            return this._symbol;
        }
    }

    static {
        sBootstrapSymbolTable = new CharsToNameCanonicalizer();
    }

    private CharsToNameCanonicalizer() {
        this._canonicalize = true;
        this._intern = true;
        this._dirty = true;
        initTables(DEFAULT_TABLE_SIZE);
    }

    private CharsToNameCanonicalizer(CharsToNameCanonicalizer charsToNameCanonicalizer, boolean z, boolean z2, String[] strArr, Bucket[] bucketArr, int i) {
        this._parent = charsToNameCanonicalizer;
        this._canonicalize = z;
        this._intern = z2;
        this._symbols = strArr;
        this._buckets = bucketArr;
        this._size = i;
        int length = strArr.length;
        this._sizeThreshold = length - (length >> 2);
        this._indexMask = length - 1;
        this._dirty = false;
    }

    public static int calcHash(String str) {
        int charAt = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            charAt = (charAt * 31) + str.charAt(i);
        }
        return charAt;
    }

    public static int calcHash(char[] cArr, int i, int i2) {
        int i3 = cArr[0];
        for (int i4 = 1; i4 < i2; i4++) {
            i3 = (i3 * 31) + cArr[i4];
        }
        return i3;
    }

    private void copyArrays() {
        Object obj = this._symbols;
        int length = obj.length;
        this._symbols = new String[length];
        System.arraycopy(obj, 0, this._symbols, 0, length);
        obj = this._buckets;
        length = obj.length;
        this._buckets = new Bucket[length];
        System.arraycopy(obj, 0, this._buckets, 0, length);
    }

    public static CharsToNameCanonicalizer createRoot() {
        return sBootstrapSymbolTable.makeOrphan();
    }

    private void initTables(int i) {
        this._symbols = new String[i];
        this._buckets = new Bucket[(i >> 1)];
        this._indexMask = i - 1;
        this._size = 0;
        this._sizeThreshold = i - (i >> 2);
    }

    private CharsToNameCanonicalizer makeOrphan() {
        return new CharsToNameCanonicalizer(null, true, true, this._symbols, this._buckets, this._size);
    }

    private synchronized void mergeChild(CharsToNameCanonicalizer charsToNameCanonicalizer) {
        if (charsToNameCanonicalizer.size() > MAX_ENTRIES_FOR_REUSE) {
            initTables(DEFAULT_TABLE_SIZE);
        } else if (charsToNameCanonicalizer.size() > size()) {
            this._symbols = charsToNameCanonicalizer._symbols;
            this._buckets = charsToNameCanonicalizer._buckets;
            this._size = charsToNameCanonicalizer._size;
            this._sizeThreshold = charsToNameCanonicalizer._sizeThreshold;
            this._indexMask = charsToNameCanonicalizer._indexMask;
        }
        this._dirty = false;
    }

    private void rehash() {
        int length = this._symbols.length;
        int i = length + length;
        if (i > MAX_TABLE_SIZE) {
            this._size = 0;
            Arrays.fill(this._symbols, null);
            Arrays.fill(this._buckets, null);
            this._dirty = true;
            return;
        }
        int i2;
        String[] strArr = this._symbols;
        Bucket[] bucketArr = this._buckets;
        this._symbols = new String[i];
        this._buckets = new Bucket[(i >> 1)];
        this._indexMask = i - 1;
        this._sizeThreshold += this._sizeThreshold;
        i = 0;
        for (i2 = 0; i2 < length; i2++) {
            String str = strArr[i2];
            if (str != null) {
                i++;
                int calcHash = calcHash(str) & this._indexMask;
                if (this._symbols[calcHash] == null) {
                    this._symbols[calcHash] = str;
                } else {
                    calcHash >>= 1;
                    this._buckets[calcHash] = new Bucket(str, this._buckets[calcHash]);
                }
            }
        }
        length >>= 1;
        int i3 = i;
        for (i2 = 0; i2 < length; i2++) {
            for (Bucket bucket = bucketArr[i2]; bucket != null; bucket = bucket.getNext()) {
                i3++;
                String symbol = bucket.getSymbol();
                int calcHash2 = calcHash(symbol) & this._indexMask;
                if (this._symbols[calcHash2] == null) {
                    this._symbols[calcHash2] = symbol;
                } else {
                    calcHash2 >>= 1;
                    this._buckets[calcHash2] = new Bucket(symbol, this._buckets[calcHash2]);
                }
            }
        }
        if (i3 != this._size) {
            throw new Error("Internal error on SymbolTable.rehash(): had " + this._size + " entries; now have " + i3 + ".");
        }
    }

    public String findSymbol(char[] cArr, int i, int i2, int i3) {
        if (i2 < 1) {
            return C2915a.f14760f;
        }
        if (!this._canonicalize) {
            return new String(cArr, i, i2);
        }
        int i4;
        int i5 = i3 & this._indexMask;
        String str = this._symbols[i5];
        if (str != null) {
            if (str.length() == i2) {
                i4 = 0;
                while (str.charAt(i4) == cArr[i + i4]) {
                    i4++;
                    if (i4 >= i2) {
                        break;
                    }
                }
                if (i4 == i2) {
                    return str;
                }
            }
            Bucket bucket = this._buckets[i5 >> 1];
            if (bucket != null) {
                String find = bucket.find(cArr, i, i2);
                if (find != null) {
                    return find;
                }
            }
        }
        if (!this._dirty) {
            copyArrays();
            this._dirty = true;
            i4 = i5;
        } else if (this._size >= this._sizeThreshold) {
            rehash();
            i4 = calcHash(cArr, i, i2) & this._indexMask;
        } else {
            i4 = i5;
        }
        this._size++;
        String str2 = new String(cArr, i, i2);
        if (this._intern) {
            str2 = InternCache.instance.intern(str2);
        }
        if (this._symbols[i4] == null) {
            this._symbols[i4] = str2;
            return str2;
        }
        i4 >>= 1;
        this._buckets[i4] = new Bucket(str2, this._buckets[i4]);
        return str2;
    }

    public synchronized CharsToNameCanonicalizer makeChild(boolean z, boolean z2) {
        return new CharsToNameCanonicalizer(this, z, z2, this._symbols, this._buckets, this._size);
    }

    public boolean maybeDirty() {
        return this._dirty;
    }

    public void release() {
        if (maybeDirty() && this._parent != null) {
            this._parent.mergeChild(this);
            this._dirty = false;
        }
    }

    public int size() {
        return this._size;
    }
}
