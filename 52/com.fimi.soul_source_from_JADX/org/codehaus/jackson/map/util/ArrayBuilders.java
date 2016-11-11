package org.codehaus.jackson.map.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public final class ArrayBuilders {
    BooleanBuilder _booleanBuilder;
    ByteBuilder _byteBuilder;
    DoubleBuilder _doubleBuilder;
    FloatBuilder _floatBuilder;
    IntBuilder _intBuilder;
    LongBuilder _longBuilder;
    ShortBuilder _shortBuilder;

    final class ArrayIterator<T> implements Iterable<T>, Iterator<T> {
        private final T[] _array;
        private int _index;

        public ArrayIterator(T[] tArr) {
            this._array = tArr;
            this._index = 0;
        }

        public boolean hasNext() {
            return this._index < this._array.length;
        }

        public Iterator<T> iterator() {
            return this;
        }

        public T next() {
            if (this._index >= this._array.length) {
                throw new NoSuchElementException();
            }
            Object[] objArr = this._array;
            int i = this._index;
            this._index = i + 1;
            return objArr[i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public final class BooleanBuilder extends PrimitiveArrayBuilder<boolean[]> {
        public final boolean[] _constructArray(int i) {
            return new boolean[i];
        }
    }

    public final class ByteBuilder extends PrimitiveArrayBuilder<byte[]> {
        public final byte[] _constructArray(int i) {
            return new byte[i];
        }
    }

    public final class DoubleBuilder extends PrimitiveArrayBuilder<double[]> {
        public final double[] _constructArray(int i) {
            return new double[i];
        }
    }

    public final class FloatBuilder extends PrimitiveArrayBuilder<float[]> {
        public final float[] _constructArray(int i) {
            return new float[i];
        }
    }

    public final class IntBuilder extends PrimitiveArrayBuilder<int[]> {
        public final int[] _constructArray(int i) {
            return new int[i];
        }
    }

    public final class LongBuilder extends PrimitiveArrayBuilder<long[]> {
        public final long[] _constructArray(int i) {
            return new long[i];
        }
    }

    public final class ShortBuilder extends PrimitiveArrayBuilder<short[]> {
        public final short[] _constructArray(int i) {
            return new short[i];
        }
    }

    public ArrayBuilders() {
        this._booleanBuilder = null;
        this._byteBuilder = null;
        this._shortBuilder = null;
        this._intBuilder = null;
        this._longBuilder = null;
        this._floatBuilder = null;
        this._doubleBuilder = null;
    }

    public static <T> List<T> addToList(List<T> list, T t) {
        if (list == null) {
            list = new ArrayList();
        }
        list.add(t);
        return list;
    }

    public static <T> Iterable<T> arrayAsIterable(T[] tArr) {
        return new ArrayIterator(tArr);
    }

    public static <T> Iterator<T> arrayAsIterator(T[] tArr) {
        return new ArrayIterator(tArr);
    }

    public static <T> HashSet<T> arrayToSet(T[] tArr) {
        HashSet<T> hashSet = new HashSet();
        if (tArr != null) {
            for (Object add : tArr) {
                hashSet.add(add);
            }
        }
        return hashSet;
    }

    public static <T> T[] insertInList(T[] tArr, T t) {
        int length = tArr.length;
        Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + 1);
        if (length > 0) {
            System.arraycopy(tArr, 0, objArr, 1, length);
        }
        objArr[0] = t;
        return objArr;
    }

    public static <T> T[] insertInListNoDup(T[] tArr, T t) {
        Object[] objArr;
        int length = tArr.length;
        int i = 0;
        while (i < length) {
            if (tArr[i] != t) {
                i++;
            } else if (i == 0) {
                return tArr;
            } else {
                objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), length);
                System.arraycopy(tArr, 0, objArr, 1, i);
                tArr[0] = t;
                return objArr;
            }
        }
        objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + 1);
        if (length > 0) {
            System.arraycopy(tArr, 0, objArr, 1, length);
        }
        objArr[0] = t;
        return objArr;
    }

    public BooleanBuilder getBooleanBuilder() {
        if (this._booleanBuilder == null) {
            this._booleanBuilder = new BooleanBuilder();
        }
        return this._booleanBuilder;
    }

    public ByteBuilder getByteBuilder() {
        if (this._byteBuilder == null) {
            this._byteBuilder = new ByteBuilder();
        }
        return this._byteBuilder;
    }

    public DoubleBuilder getDoubleBuilder() {
        if (this._doubleBuilder == null) {
            this._doubleBuilder = new DoubleBuilder();
        }
        return this._doubleBuilder;
    }

    public FloatBuilder getFloatBuilder() {
        if (this._floatBuilder == null) {
            this._floatBuilder = new FloatBuilder();
        }
        return this._floatBuilder;
    }

    public IntBuilder getIntBuilder() {
        if (this._intBuilder == null) {
            this._intBuilder = new IntBuilder();
        }
        return this._intBuilder;
    }

    public LongBuilder getLongBuilder() {
        if (this._longBuilder == null) {
            this._longBuilder = new LongBuilder();
        }
        return this._longBuilder;
    }

    public ShortBuilder getShortBuilder() {
        if (this._shortBuilder == null) {
            this._shortBuilder = new ShortBuilder();
        }
        return this._shortBuilder;
    }
}
