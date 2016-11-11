package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.detector.Detector;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiDetector extends Detector {
    private static final DetectorResult[] EMPTY_DETECTOR_RESULTS;

    static {
        EMPTY_DETECTOR_RESULTS = new DetectorResult[0];
    }

    public MultiDetector(BitMatrix bitMatrix) {
        super(bitMatrix);
    }

    public DetectorResult[] detectMulti(Hashtable hashtable) {
        int i = 0;
        FinderPatternInfo[] findMulti = new MultiFinderPatternFinder(getImage()).findMulti(hashtable);
        if (findMulti == null || findMulti.length == 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        Vector vector = new Vector();
        for (FinderPatternInfo processFinderPatternInfo : findMulti) {
            try {
                vector.addElement(processFinderPatternInfo(processFinderPatternInfo));
            } catch (ReaderException e) {
            }
        }
        if (vector.isEmpty()) {
            return EMPTY_DETECTOR_RESULTS;
        }
        DetectorResult[] detectorResultArr = new DetectorResult[vector.size()];
        while (i < vector.size()) {
            detectorResultArr[i] = (DetectorResult) vector.elementAt(i);
            i++;
        }
        return detectorResultArr;
    }
}
