package org.codehaus.jackson.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.util.BufferRecycler.CharBufferType;
import org.p122a.p123a.C2915a;

public final class TextBuffer {
    static final int MAX_SEGMENT_LEN = 262144;
    static final int MIN_SEGMENT_LEN = 1000;
    static final char[] NO_CHARS;
    private final BufferRecycler _allocator;
    private char[] _currentSegment;
    private int _currentSize;
    private boolean _hasSegments;
    private char[] _inputBuffer;
    private int _inputLen;
    private int _inputStart;
    private char[] _resultArray;
    private String _resultString;
    private int _segmentSize;
    private ArrayList<char[]> _segments;

    static {
        NO_CHARS = new char[0];
    }

    public TextBuffer(BufferRecycler bufferRecycler) {
        this._hasSegments = false;
        this._allocator = bufferRecycler;
    }

    private final char[] _charArray(int i) {
        return new char[i];
    }

    private char[] buildResultArray() {
        if (this._resultString != null) {
            return this._resultString.toCharArray();
        }
        if (this._inputStart < 0) {
            int size = size();
            if (size < 1) {
                return NO_CHARS;
            }
            Object _charArray = _charArray(size);
            if (this._segments != null) {
                int size2 = this._segments.size();
                int i = 0;
                for (int i2 = 0; i2 < size2; i2++) {
                    char[] cArr = (char[]) this._segments.get(i2);
                    int length = cArr.length;
                    System.arraycopy(cArr, 0, _charArray, i, length);
                    i += length;
                }
                size = i;
            } else {
                size = 0;
            }
            System.arraycopy(this._currentSegment, 0, _charArray, size, this._currentSize);
            return _charArray;
        } else if (this._inputLen < 1) {
            return NO_CHARS;
        } else {
            Object _charArray2 = _charArray(this._inputLen);
            System.arraycopy(this._inputBuffer, this._inputStart, _charArray2, 0, this._inputLen);
            return _charArray2;
        }
    }

    private final void clearSegments() {
        this._hasSegments = false;
        this._segments.clear();
        this._segmentSize = 0;
        this._currentSize = 0;
    }

    private void expand(int i) {
        if (this._segments == null) {
            this._segments = new ArrayList();
        }
        Object obj = this._currentSegment;
        this._hasSegments = true;
        this._segments.add(obj);
        this._segmentSize += obj.length;
        int length = obj.length;
        int i2 = length >> 1;
        if (i2 >= i) {
            i = i2;
        }
        char[] _charArray = _charArray(Math.min(MAX_SEGMENT_LEN, length + i));
        this._currentSize = 0;
        this._currentSegment = _charArray;
    }

    private final char[] findBuffer(int i) {
        return this._allocator != null ? this._allocator.allocCharBuffer(CharBufferType.TEXT_BUFFER, i) : new char[Math.max(i, MIN_SEGMENT_LEN)];
    }

    private void unshare(int i) {
        int i2 = this._inputLen;
        this._inputLen = 0;
        Object obj = this._inputBuffer;
        this._inputBuffer = null;
        int i3 = this._inputStart;
        this._inputStart = -1;
        int i4 = i2 + i;
        if (this._currentSegment == null || i4 > this._currentSegment.length) {
            this._currentSegment = findBuffer(i4);
        }
        if (i2 > 0) {
            System.arraycopy(obj, i3, this._currentSegment, 0, i2);
        }
        this._segmentSize = 0;
        this._currentSize = i2;
    }

    public void append(char c) {
        if (this._inputStart >= 0) {
            unshare(16);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        if (this._currentSize >= cArr.length) {
            expand(1);
            cArr = this._currentSegment;
        }
        int i = this._currentSize;
        this._currentSize = i + 1;
        cArr[i] = c;
    }

    public void append(String str, int i, int i2) {
        if (this._inputStart >= 0) {
            unshare(i2);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        int length = cArr.length - this._currentSize;
        if (length >= i2) {
            str.getChars(i, i + i2, cArr, this._currentSize);
            this._currentSize += i2;
            return;
        }
        if (length > 0) {
            str.getChars(i, i + length, cArr, this._currentSize);
            i2 -= length;
            i += length;
        }
        expand(i2);
        str.getChars(i, i + i2, this._currentSegment, 0);
        this._currentSize = i2;
    }

    public void append(char[] cArr, int i, int i2) {
        if (this._inputStart >= 0) {
            unshare(i2);
        }
        this._resultString = null;
        this._resultArray = null;
        Object obj = this._currentSegment;
        int length = obj.length - this._currentSize;
        if (length >= i2) {
            System.arraycopy(cArr, i, obj, this._currentSize, i2);
            this._currentSize += i2;
            return;
        }
        if (length > 0) {
            System.arraycopy(cArr, i, obj, this._currentSize, length);
            i += length;
            i2 -= length;
        }
        expand(i2);
        System.arraycopy(cArr, i, this._currentSegment, 0, i2);
        this._currentSize = i2;
    }

    public char[] contentsAsArray() {
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr;
        }
        cArr = buildResultArray();
        this._resultArray = cArr;
        return cArr;
    }

    public BigDecimal contentsAsDecimal() {
        return this._resultArray != null ? new BigDecimal(this._resultArray) : this._inputStart >= 0 ? new BigDecimal(this._inputBuffer, this._inputStart, this._inputLen) : this._segmentSize == 0 ? new BigDecimal(this._currentSegment, 0, this._currentSize) : new BigDecimal(contentsAsArray());
    }

    public double contentsAsDouble() {
        return NumberInput.parseDouble(contentsAsString());
    }

    public String contentsAsString() {
        if (this._resultString == null) {
            if (this._resultArray != null) {
                this._resultString = new String(this._resultArray);
            } else if (this._inputStart < 0) {
                int i = this._segmentSize;
                int i2 = this._currentSize;
                if (i == 0) {
                    this._resultString = i2 == 0 ? C2915a.f14760f : new String(this._currentSegment, 0, i2);
                } else {
                    StringBuilder stringBuilder = new StringBuilder(i + i2);
                    if (this._segments != null) {
                        int size = this._segments.size();
                        for (i2 = 0; i2 < size; i2++) {
                            char[] cArr = (char[]) this._segments.get(i2);
                            stringBuilder.append(cArr, 0, cArr.length);
                        }
                    }
                    stringBuilder.append(this._currentSegment, 0, this._currentSize);
                    this._resultString = stringBuilder.toString();
                }
            } else if (this._inputLen < 1) {
                String str = C2915a.f14760f;
                this._resultString = str;
                return str;
            } else {
                this._resultString = new String(this._inputBuffer, this._inputStart, this._inputLen);
            }
        }
        return this._resultString;
    }

    public final char[] emptyAndGetCurrentSegment() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        char[] cArr = this._currentSegment;
        if (cArr != null) {
            return cArr;
        }
        cArr = findBuffer(0);
        this._currentSegment = cArr;
        return cArr;
    }

    public void ensureNotShared() {
        if (this._inputStart >= 0) {
            unshare(16);
        }
    }

    public char[] expandCurrentSegment() {
        Object obj = this._currentSegment;
        int length = obj.length;
        this._currentSegment = _charArray(length == MAX_SEGMENT_LEN ? 262145 : Math.min(MAX_SEGMENT_LEN, (length >> 1) + length));
        System.arraycopy(obj, 0, this._currentSegment, 0, length);
        return this._currentSegment;
    }

    public char[] finishCurrentSegment() {
        if (this._segments == null) {
            this._segments = new ArrayList();
        }
        this._hasSegments = true;
        this._segments.add(this._currentSegment);
        int length = this._currentSegment.length;
        this._segmentSize += length;
        char[] _charArray = _charArray(Math.min(length + (length >> 1), MAX_SEGMENT_LEN));
        this._currentSize = 0;
        this._currentSegment = _charArray;
        return _charArray;
    }

    public char[] getCurrentSegment() {
        if (this._inputStart >= 0) {
            unshare(1);
        } else {
            char[] cArr = this._currentSegment;
            if (cArr == null) {
                this._currentSegment = findBuffer(0);
            } else if (this._currentSize >= cArr.length) {
                expand(1);
            }
        }
        return this._currentSegment;
    }

    public int getCurrentSegmentSize() {
        return this._currentSize;
    }

    public char[] getTextBuffer() {
        if (this._inputStart >= 0) {
            return this._inputBuffer;
        }
        if (this._resultArray != null) {
            return this._resultArray;
        }
        if (this._resultString == null) {
            return !this._hasSegments ? this._currentSegment : contentsAsArray();
        } else {
            char[] toCharArray = this._resultString.toCharArray();
            this._resultArray = toCharArray;
            return toCharArray;
        }
    }

    public int getTextOffset() {
        return this._inputStart >= 0 ? this._inputStart : 0;
    }

    public void releaseBuffers() {
        if (this._allocator == null) {
            resetWithEmpty();
        } else if (this._currentSegment != null) {
            resetWithEmpty();
            char[] cArr = this._currentSegment;
            this._currentSegment = null;
            this._allocator.releaseCharBuffer(CharBufferType.TEXT_BUFFER, cArr);
        }
    }

    public void resetWithCopy(char[] cArr, int i, int i2) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = findBuffer(i2);
        }
        this._segmentSize = 0;
        this._currentSize = 0;
        append(cArr, i, i2);
    }

    public void resetWithEmpty() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithShared(char[] cArr, int i, int i2) {
        this._resultString = null;
        this._resultArray = null;
        this._inputBuffer = cArr;
        this._inputStart = i;
        this._inputLen = i2;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithString(String str) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = str;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        this._currentSize = 0;
    }

    public void setCurrentLength(int i) {
        this._currentSize = i;
    }

    public int size() {
        return this._inputStart >= 0 ? this._inputLen : this._resultArray != null ? this._resultArray.length : this._resultString != null ? this._resultString.length() : this._segmentSize + this._currentSize;
    }

    public String toString() {
        return contentsAsString();
    }
}
