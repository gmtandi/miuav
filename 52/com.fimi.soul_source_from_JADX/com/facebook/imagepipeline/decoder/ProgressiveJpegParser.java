package com.facebook.imagepipeline.decoder;

import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.util.StreamUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import com.facebook.imagepipeline.memory.PooledByteArrayBufferedInputStream;
import com.facebook.imageutils.JfifUtil;
import com.tencent.mm.sdk.platformtools.Util;
import it.p074a.p075a.C2799f;
import java.io.IOException;
import java.io.InputStream;

public class ProgressiveJpegParser {
    private static final int BUFFER_SIZE = 16384;
    private static final int NOT_A_JPEG = 6;
    private static final int READ_FIRST_JPEG_BYTE = 0;
    private static final int READ_MARKER_FIRST_BYTE_OR_ENTROPY_DATA = 2;
    private static final int READ_MARKER_SECOND_BYTE = 3;
    private static final int READ_SECOND_JPEG_BYTE = 1;
    private static final int READ_SIZE_FIRST_BYTE = 4;
    private static final int READ_SIZE_SECOND_BYTE = 5;
    private int mBestScanEndOffset;
    private int mBestScanNumber;
    private final ByteArrayPool mByteArrayPool;
    private int mBytesParsed;
    private int mLastByteRead;
    private int mNextFullScanNumber;
    private int mParserState;

    public ProgressiveJpegParser(ByteArrayPool byteArrayPool) {
        this.mByteArrayPool = (ByteArrayPool) Preconditions.checkNotNull(byteArrayPool);
        this.mBytesParsed = READ_FIRST_JPEG_BYTE;
        this.mLastByteRead = READ_FIRST_JPEG_BYTE;
        this.mNextFullScanNumber = READ_FIRST_JPEG_BYTE;
        this.mBestScanEndOffset = READ_FIRST_JPEG_BYTE;
        this.mBestScanNumber = READ_FIRST_JPEG_BYTE;
        this.mParserState = READ_FIRST_JPEG_BYTE;
    }

    private boolean doParseMoreData(InputStream inputStream) {
        int i = this.mBestScanNumber;
        while (this.mParserState != NOT_A_JPEG) {
            int read = inputStream.read();
            if (read == -1) {
                return this.mParserState == NOT_A_JPEG && this.mBestScanNumber != i;
            } else {
                this.mBytesParsed += READ_SECOND_JPEG_BYTE;
                switch (this.mParserState) {
                    case READ_FIRST_JPEG_BYTE /*0*/:
                        if (read != Util.MASK_8BIT) {
                            this.mParserState = NOT_A_JPEG;
                            break;
                        }
                        try {
                            this.mParserState = READ_SECOND_JPEG_BYTE;
                            break;
                        } catch (Throwable e) {
                            Throwables.propagate(e);
                            break;
                        }
                    case READ_SECOND_JPEG_BYTE /*1*/:
                        if (read != JfifUtil.MARKER_SOI) {
                            this.mParserState = NOT_A_JPEG;
                            break;
                        }
                        this.mParserState = READ_MARKER_FIRST_BYTE_OR_ENTROPY_DATA;
                        break;
                    case READ_MARKER_FIRST_BYTE_OR_ENTROPY_DATA /*2*/:
                        if (read == Util.MASK_8BIT) {
                            this.mParserState = READ_MARKER_SECOND_BYTE;
                            break;
                        }
                        break;
                    case READ_MARKER_SECOND_BYTE /*3*/:
                        if (read != Util.MASK_8BIT) {
                            if (read != 0) {
                                if (read == JfifUtil.MARKER_SOS || read == JfifUtil.MARKER_EOI) {
                                    newScanOrImageEndFound(this.mBytesParsed - 2);
                                }
                                if (!doesMarkerStartSegment(read)) {
                                    this.mParserState = READ_MARKER_FIRST_BYTE_OR_ENTROPY_DATA;
                                    break;
                                }
                                this.mParserState = READ_SIZE_FIRST_BYTE;
                                break;
                            }
                            this.mParserState = READ_MARKER_FIRST_BYTE_OR_ENTROPY_DATA;
                            break;
                        }
                        this.mParserState = READ_MARKER_SECOND_BYTE;
                        break;
                    case READ_SIZE_FIRST_BYTE /*4*/:
                        this.mParserState = READ_SIZE_SECOND_BYTE;
                        break;
                    case READ_SIZE_SECOND_BYTE /*5*/:
                        int i2 = ((this.mLastByteRead << 8) + read) - 2;
                        StreamUtil.skip(inputStream, (long) i2);
                        this.mBytesParsed = i2 + this.mBytesParsed;
                        this.mParserState = READ_MARKER_FIRST_BYTE_OR_ENTROPY_DATA;
                        break;
                    default:
                        Preconditions.checkState(false);
                        break;
                }
                this.mLastByteRead = read;
            }
        }
        if (this.mParserState == NOT_A_JPEG) {
        }
    }

    private static boolean doesMarkerStartSegment(int i) {
        boolean z = true;
        if (i == READ_SECOND_JPEG_BYTE) {
            return false;
        }
        if (i >= JfifUtil.MARKER_RST0 && i <= C2799f.f14288z) {
            return false;
        }
        if (i == JfifUtil.MARKER_EOI || i == JfifUtil.MARKER_SOI) {
            z = false;
        }
        return z;
    }

    private void newScanOrImageEndFound(int i) {
        if (this.mNextFullScanNumber > 0) {
            this.mBestScanEndOffset = i;
        }
        int i2 = this.mNextFullScanNumber;
        this.mNextFullScanNumber = i2 + READ_SECOND_JPEG_BYTE;
        this.mBestScanNumber = i2;
    }

    public int getBestScanEndOffset() {
        return this.mBestScanEndOffset;
    }

    public int getBestScanNumber() {
        return this.mBestScanNumber;
    }

    public boolean isJpeg() {
        return this.mBytesParsed > READ_SECOND_JPEG_BYTE && this.mParserState != NOT_A_JPEG;
    }

    public boolean parseMoreData(EncodedImage encodedImage) {
        if (this.mParserState == NOT_A_JPEG) {
            return false;
        }
        if (encodedImage.getSize() <= this.mBytesParsed) {
            return false;
        }
        InputStream pooledByteArrayBufferedInputStream = new PooledByteArrayBufferedInputStream(encodedImage.getInputStream(), (byte[]) this.mByteArrayPool.get(BUFFER_SIZE), this.mByteArrayPool);
        boolean doParseMoreData;
        try {
            StreamUtil.skip(pooledByteArrayBufferedInputStream, (long) this.mBytesParsed);
            doParseMoreData = doParseMoreData(pooledByteArrayBufferedInputStream);
            return doParseMoreData;
        } catch (IOException e) {
            doParseMoreData = e;
            Throwables.propagate(doParseMoreData);
            return false;
        } finally {
            Closeables.closeQuietly(pooledByteArrayBufferedInputStream);
        }
    }
}
