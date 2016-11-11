package org.codehaus.jackson.format;

import java.io.InputStream;
import java.util.Collection;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.format.InputAccessor.Std;

public class DataFormatDetector {
    public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
    protected final JsonFactory[] _detectors;
    protected final int _maxInputLookahead;
    protected final MatchStrength _minimalMatch;
    protected final MatchStrength _optimalMatch;

    public DataFormatDetector(Collection<JsonFactory> collection) {
        this((JsonFactory[]) collection.toArray(new JsonFactory[collection.size()]));
    }

    public DataFormatDetector(JsonFactory... jsonFactoryArr) {
        this(jsonFactoryArr, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, DEFAULT_MAX_INPUT_LOOKAHEAD);
    }

    private DataFormatDetector(JsonFactory[] jsonFactoryArr, MatchStrength matchStrength, MatchStrength matchStrength2, int i) {
        this._detectors = jsonFactoryArr;
        this._optimalMatch = matchStrength;
        this._minimalMatch = matchStrength2;
        this._maxInputLookahead = i;
    }

    private DataFormatMatcher _findFormat(Std std) {
        JsonFactory jsonFactory;
        MatchStrength hasFormat;
        JsonFactory[] jsonFactoryArr = this._detectors;
        int length = jsonFactoryArr.length;
        int i = 0;
        JsonFactory jsonFactory2 = null;
        MatchStrength matchStrength = null;
        while (i < length) {
            JsonFactory jsonFactory3;
            jsonFactory = jsonFactoryArr[i];
            std.reset();
            hasFormat = jsonFactory.hasFormat(std);
            if (hasFormat == null) {
                jsonFactory3 = jsonFactory2;
            } else if (hasFormat.ordinal() < this._minimalMatch.ordinal()) {
                jsonFactory3 = jsonFactory2;
            } else if (jsonFactory2 != null && matchStrength.ordinal() >= hasFormat.ordinal()) {
                jsonFactory3 = jsonFactory2;
            } else if (hasFormat.ordinal() >= this._optimalMatch.ordinal()) {
                break;
            } else {
                matchStrength = hasFormat;
                jsonFactory3 = jsonFactory;
            }
            i++;
            jsonFactory2 = jsonFactory3;
        }
        hasFormat = matchStrength;
        jsonFactory = jsonFactory2;
        return std.createMatcher(jsonFactory, hasFormat);
    }

    public DataFormatMatcher findFormat(InputStream inputStream) {
        return _findFormat(new Std(inputStream, new byte[this._maxInputLookahead]));
    }

    public DataFormatMatcher findFormat(byte[] bArr) {
        return _findFormat(new Std(bArr));
    }

    public DataFormatDetector withMaxInputLookahead(int i) {
        return i == this._maxInputLookahead ? this : new DataFormatDetector(this._detectors, this._optimalMatch, this._minimalMatch, i);
    }

    public DataFormatDetector withMinimalMatch(MatchStrength matchStrength) {
        return matchStrength == this._minimalMatch ? this : new DataFormatDetector(this._detectors, this._optimalMatch, matchStrength, this._maxInputLookahead);
    }

    public DataFormatDetector withOptimalMatch(MatchStrength matchStrength) {
        return matchStrength == this._optimalMatch ? this : new DataFormatDetector(this._detectors, matchStrength, this._minimalMatch, this._maxInputLookahead);
    }
}
