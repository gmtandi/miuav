package org.codehaus.jackson.sym;

import com.tencent.mm.sdk.platformtools.Util;
import java.util.Arrays;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.InternCache;

public final class BytesToNameCanonicalizer {
    protected static final int DEFAULT_TABLE_SIZE = 64;
    static final int INITIAL_COLLISION_LEN = 32;
    static final int LAST_VALID_BUCKET = 254;
    static final int MAX_ENTRIES_FOR_REUSE = 6000;
    protected static final int MAX_TABLE_SIZE = 65536;
    static final int MIN_HASH_SIZE = 16;
    private int _collCount;
    private int _collEnd;
    private Bucket[] _collList;
    private boolean _collListShared;
    private int _count;
    final boolean _intern;
    private int[] _mainHash;
    private int _mainHashMask;
    private boolean _mainHashShared;
    private Name[] _mainNames;
    private boolean _mainNamesShared;
    private transient boolean _needRehash;
    final BytesToNameCanonicalizer _parent;

    final class Bucket {
        protected final Name _name;
        protected final Bucket _next;

        Bucket(Name name, Bucket bucket) {
            this._name = name;
            this._next = bucket;
        }

        public Name find(int i, int i2, int i3) {
            if (this._name.hashCode() == i && this._name.equals(i2, i3)) {
                return this._name;
            }
            for (Bucket bucket = this._next; bucket != null; bucket = bucket._next) {
                Name name = bucket._name;
                if (name.hashCode() == i && name.equals(i2, i3)) {
                    return name;
                }
            }
            return null;
        }

        public Name find(int i, int[] iArr, int i2) {
            if (this._name.hashCode() == i && this._name.equals(iArr, i2)) {
                return this._name;
            }
            for (Bucket bucket = this._next; bucket != null; bucket = bucket._next) {
                Name name = bucket._name;
                if (name.hashCode() == i && name.equals(iArr, i2)) {
                    return name;
                }
            }
            return null;
        }

        public int length() {
            int i = 1;
            for (Bucket bucket = this._next; bucket != null; bucket = bucket._next) {
                i++;
            }
            return i;
        }
    }

    private BytesToNameCanonicalizer(int i, boolean z) {
        int i2 = MIN_HASH_SIZE;
        this._parent = null;
        this._intern = z;
        if (i < MIN_HASH_SIZE) {
            i = MIN_HASH_SIZE;
        } else if (((i - 1) & i) != 0) {
            while (i2 < i) {
                i2 += i2;
            }
            i = i2;
        }
        initTables(i);
    }

    private BytesToNameCanonicalizer(BytesToNameCanonicalizer bytesToNameCanonicalizer, boolean z) {
        this._parent = bytesToNameCanonicalizer;
        this._intern = z;
        this._count = bytesToNameCanonicalizer._count;
        this._mainHashMask = bytesToNameCanonicalizer._mainHashMask;
        this._mainHash = bytesToNameCanonicalizer._mainHash;
        this._mainNames = bytesToNameCanonicalizer._mainNames;
        this._collList = bytesToNameCanonicalizer._collList;
        this._collCount = bytesToNameCanonicalizer._collCount;
        this._collEnd = bytesToNameCanonicalizer._collEnd;
        this._needRehash = false;
        this._mainHashShared = true;
        this._mainNamesShared = true;
        this._collListShared = true;
    }

    private void _addSymbol(int i, Name name) {
        int i2;
        if (this._mainHashShared) {
            unshareMain();
        }
        if (this._needRehash) {
            rehash();
        }
        this._count++;
        int i3 = i & this._mainHashMask;
        if (this._mainNames[i3] == null) {
            this._mainHash[i3] = i << 8;
            if (this._mainNamesShared) {
                unshareNames();
            }
            this._mainNames[i3] = name;
        } else {
            if (this._collListShared) {
                unshareCollision();
            }
            this._collCount++;
            int i4 = this._mainHash[i3];
            i2 = i4 & Util.MASK_8BIT;
            if (i2 == 0) {
                if (this._collEnd <= LAST_VALID_BUCKET) {
                    i2 = this._collEnd;
                    this._collEnd++;
                    if (i2 >= this._collList.length) {
                        expandCollision();
                    }
                } else {
                    i2 = findBestBucket();
                }
                this._mainHash[i3] = (i4 & -256) | (i2 + 1);
            } else {
                i2--;
            }
            this._collList[i2] = new Bucket(name, this._collList[i2]);
        }
        i2 = this._mainHash.length;
        if (this._count > (i2 >> 1)) {
            i3 = i2 >> 2;
            if (this._count > i2 - i3) {
                this._needRehash = true;
            } else if (this._collCount >= i3) {
                this._needRehash = true;
            }
        }
    }

    public static final int calcHash(int i) {
        int i2 = (i >>> MIN_HASH_SIZE) ^ i;
        return i2 ^ (i2 >>> 8);
    }

    public static final int calcHash(int i, int i2) {
        int i3 = (i * 31) + i2;
        i3 ^= i3 >>> MIN_HASH_SIZE;
        return i3 ^ (i3 >>> 8);
    }

    public static final int calcHash(int[] iArr, int i) {
        int i2;
        int i3 = iArr[0];
        for (i2 = 1; i2 < i; i2++) {
            i3 = (i3 * 31) + iArr[i2];
        }
        i2 = (i3 >>> MIN_HASH_SIZE) ^ i3;
        return i2 ^ (i2 >>> 8);
    }

    private static Name constructName(int i, String str, int i2, int i3) {
        return i3 == 0 ? new Name1(str, i, i2) : new Name2(str, i, i2, i3);
    }

    private static Name constructName(int i, String str, int[] iArr, int i2) {
        if (i2 < 4) {
            switch (i2) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return new Name1(str, i, iArr[0]);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    return new Name2(str, i, iArr[0], iArr[1]);
                case Type.BYTE /*3*/:
                    return new Name3(str, i, iArr[0], iArr[1], iArr[2]);
            }
        }
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr2[i3] = iArr[i3];
        }
        return new NameN(str, i, iArr2, i2);
    }

    public static BytesToNameCanonicalizer createRoot() {
        return new BytesToNameCanonicalizer((int) DEFAULT_TABLE_SIZE, true);
    }

    private void expandCollision() {
        Object obj = this._collList;
        int length = obj.length;
        this._collList = new Bucket[(length + length)];
        System.arraycopy(obj, 0, this._collList, 0, length);
    }

    private int findBestBucket() {
        Bucket[] bucketArr = this._collList;
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        int i3 = 0;
        int i4 = this._collEnd;
        while (i3 < i4) {
            int length = bucketArr[i3].length();
            if (length >= i) {
                length = i;
            } else if (length == 1) {
                return i3;
            } else {
                i2 = i3;
            }
            i3++;
            i = length;
        }
        return i2;
    }

    public static Name getEmptyName() {
        return Name1.getEmptyName();
    }

    private void initTables(int i) {
        this._count = 0;
        this._mainHash = new int[i];
        this._mainNames = new Name[i];
        this._mainHashShared = false;
        this._mainNamesShared = false;
        this._mainHashMask = i - 1;
        this._collListShared = true;
        this._collList = null;
        this._collEnd = 0;
        this._needRehash = false;
    }

    private void markAsShared() {
        this._mainHashShared = true;
        this._mainNamesShared = true;
        this._collListShared = true;
    }

    private synchronized void mergeChild(BytesToNameCanonicalizer bytesToNameCanonicalizer) {
        if (bytesToNameCanonicalizer._count > this._count) {
            if (bytesToNameCanonicalizer.size() > MAX_ENTRIES_FOR_REUSE) {
                initTables(DEFAULT_TABLE_SIZE);
            } else {
                this._count = bytesToNameCanonicalizer._count;
                this._mainHash = bytesToNameCanonicalizer._mainHash;
                this._mainNames = bytesToNameCanonicalizer._mainNames;
                this._mainHashShared = true;
                this._mainNamesShared = true;
                this._mainHashMask = bytesToNameCanonicalizer._mainHashMask;
                this._collList = bytesToNameCanonicalizer._collList;
                this._collCount = bytesToNameCanonicalizer._collCount;
                this._collEnd = bytesToNameCanonicalizer._collEnd;
            }
        }
    }

    private void nukeSymbols() {
        this._count = 0;
        Arrays.fill(this._mainHash, 0);
        Arrays.fill(this._mainNames, null);
        Arrays.fill(this._collList, null);
        this._collCount = 0;
        this._collEnd = 0;
    }

    private void rehash() {
        this._needRehash = false;
        this._mainNamesShared = false;
        int length = this._mainHash.length;
        int i = length + length;
        if (i > MAX_TABLE_SIZE) {
            nukeSymbols();
            return;
        }
        int i2;
        this._mainHash = new int[i];
        this._mainHashMask = i - 1;
        Name[] nameArr = this._mainNames;
        this._mainNames = new Name[i];
        i = 0;
        for (i2 = 0; i2 < length; i2++) {
            Name name = nameArr[i2];
            if (name != null) {
                i++;
                int hashCode = name.hashCode();
                int i3 = this._mainHashMask & hashCode;
                this._mainNames[i3] = name;
                this._mainHash[i3] = hashCode << 8;
            }
        }
        int i4 = this._collEnd;
        if (i4 != 0) {
            this._collCount = 0;
            this._collEnd = 0;
            this._collListShared = false;
            Bucket[] bucketArr = this._collList;
            this._collList = new Bucket[bucketArr.length];
            length = 0;
            int i5 = i;
            while (length < i4) {
                i = i5;
                Bucket bucket = bucketArr[length];
                while (bucket != null) {
                    i2 = i + 1;
                    Name name2 = bucket._name;
                    i = name2.hashCode();
                    i3 = this._mainHashMask & i;
                    int i6 = this._mainHash[i3];
                    if (this._mainNames[i3] == null) {
                        this._mainHash[i3] = i << 8;
                        this._mainNames[i3] = name2;
                    } else {
                        this._collCount++;
                        i = i6 & Util.MASK_8BIT;
                        if (i == 0) {
                            if (this._collEnd <= LAST_VALID_BUCKET) {
                                i = this._collEnd;
                                this._collEnd++;
                                if (i >= this._collList.length) {
                                    expandCollision();
                                }
                            } else {
                                i = findBestBucket();
                            }
                            this._mainHash[i3] = (i6 & -256) | (i + 1);
                        } else {
                            i--;
                        }
                        this._collList[i] = new Bucket(name2, this._collList[i]);
                    }
                    bucket = bucket._next;
                    i = i2;
                }
                length++;
                i5 = i;
            }
            if (i5 != this._count) {
                throw new RuntimeException("Internal error: count after rehash " + i5 + "; should be " + this._count);
            }
        }
    }

    private void unshareCollision() {
        Object obj = this._collList;
        if (obj == null) {
            this._collList = new Bucket[INITIAL_COLLISION_LEN];
        } else {
            int length = obj.length;
            this._collList = new Bucket[length];
            System.arraycopy(obj, 0, this._collList, 0, length);
        }
        this._collListShared = false;
    }

    private void unshareMain() {
        Object obj = this._mainHash;
        int length = this._mainHash.length;
        this._mainHash = new int[length];
        System.arraycopy(obj, 0, this._mainHash, 0, length);
        this._mainHashShared = false;
    }

    private void unshareNames() {
        Object obj = this._mainNames;
        int length = obj.length;
        this._mainNames = new Name[length];
        System.arraycopy(obj, 0, this._mainNames, 0, length);
        this._mainNamesShared = false;
    }

    public Name addName(String str, int i, int i2) {
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int calcHash = i2 == 0 ? calcHash(i) : calcHash(i, i2);
        Name constructName = constructName(calcHash, str, i, i2);
        _addSymbol(calcHash, constructName);
        return constructName;
    }

    public Name addName(String str, int[] iArr, int i) {
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int calcHash = calcHash(iArr, i);
        Name constructName = constructName(calcHash, str, iArr, i);
        _addSymbol(calcHash, constructName);
        return constructName;
    }

    public Name findName(int i) {
        int calcHash = calcHash(i);
        int i2 = this._mainHashMask & calcHash;
        int i3 = this._mainHash[i2];
        if ((((i3 >> 8) ^ calcHash) << 8) == 0) {
            Name name = this._mainNames[i2];
            if (name == null) {
                return null;
            }
            if (name.equals(i)) {
                return name;
            }
        } else if (i3 == 0) {
            return null;
        }
        i2 = i3 & Util.MASK_8BIT;
        if (i2 <= 0) {
            return null;
        }
        Bucket bucket = this._collList[i2 - 1];
        return bucket != null ? bucket.find(calcHash, i, 0) : null;
    }

    public Name findName(int i, int i2) {
        int calcHash = calcHash(i, i2);
        int i3 = this._mainHashMask & calcHash;
        int i4 = this._mainHash[i3];
        if ((((i4 >> 8) ^ calcHash) << 8) == 0) {
            Name name = this._mainNames[i3];
            if (name == null) {
                return null;
            }
            if (name.equals(i, i2)) {
                return name;
            }
        } else if (i4 == 0) {
            return null;
        }
        i3 = i4 & Util.MASK_8BIT;
        if (i3 <= 0) {
            return null;
        }
        Bucket bucket = this._collList[i3 - 1];
        return bucket != null ? bucket.find(calcHash, i, i2) : null;
    }

    public Name findName(int[] iArr, int i) {
        int calcHash = calcHash(iArr, i);
        int i2 = this._mainHashMask & calcHash;
        int i3 = this._mainHash[i2];
        if ((((i3 >> 8) ^ calcHash) << 8) == 0) {
            Name name = this._mainNames[i2];
            if (name == null || name.equals(iArr, i)) {
                return name;
            }
        } else if (i3 == 0) {
            return null;
        }
        i2 = i3 & Util.MASK_8BIT;
        if (i2 > 0) {
            Bucket bucket = this._collList[i2 - 1];
            if (bucket != null) {
                return bucket.find(calcHash, iArr, i);
            }
        }
        return null;
    }

    public synchronized BytesToNameCanonicalizer makeChild(boolean z, boolean z2) {
        return new BytesToNameCanonicalizer(this, z2);
    }

    public boolean maybeDirty() {
        return !this._mainHashShared;
    }

    public void release() {
        if (maybeDirty() && this._parent != null) {
            this._parent.mergeChild(this);
            markAsShared();
        }
    }

    public int size() {
        return this._count;
    }
}
