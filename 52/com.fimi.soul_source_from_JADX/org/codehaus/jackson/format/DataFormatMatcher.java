package org.codehaus.jackson.format;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.io.MergedStream;

public class DataFormatMatcher {
    protected final byte[] _bufferedData;
    protected final int _bufferedLength;
    protected final JsonFactory _match;
    protected final MatchStrength _matchStrength;
    protected final InputStream _originalStream;

    protected DataFormatMatcher(InputStream inputStream, byte[] bArr, int i, JsonFactory jsonFactory, MatchStrength matchStrength) {
        this._originalStream = inputStream;
        this._bufferedData = bArr;
        this._bufferedLength = i;
        this._match = jsonFactory;
        this._matchStrength = matchStrength;
    }

    public JsonParser createParserWithMatch() {
        return this._match == null ? null : this._originalStream == null ? this._match.createJsonParser(this._bufferedData, 0, this._bufferedLength) : this._match.createJsonParser(getDataStream());
    }

    public InputStream getDataStream() {
        return this._originalStream == null ? new ByteArrayInputStream(this._bufferedData, 0, this._bufferedLength) : new MergedStream(null, this._originalStream, this._bufferedData, 0, this._bufferedLength);
    }

    public JsonFactory getMatch() {
        return this._match;
    }

    public MatchStrength getMatchStrength() {
        return this._matchStrength == null ? MatchStrength.INCONCLUSIVE : this._matchStrength;
    }

    public String getMatchedFormatName() {
        return this._match.getFormatName();
    }

    public boolean hasMatch() {
        return this._match != null;
    }
}
