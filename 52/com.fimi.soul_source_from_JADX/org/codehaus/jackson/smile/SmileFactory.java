package org.codehaus.jackson.smile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.format.InputAccessor;
import org.codehaus.jackson.format.MatchStrength;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.smile.SmileParser.Feature;

public class SmileFactory extends JsonFactory {
    static final int DEFAULT_SMILE_GENERATOR_FEATURE_FLAGS;
    static final int DEFAULT_SMILE_PARSER_FEATURE_FLAGS;
    public static final String FORMAT_NAME_SMILE = "Smile";
    protected boolean _cfgDelegateToTextual;
    protected int _smileGeneratorFeatures;
    protected int _smileParserFeatures;

    static {
        DEFAULT_SMILE_PARSER_FEATURE_FLAGS = Feature.collectDefaults();
        DEFAULT_SMILE_GENERATOR_FEATURE_FLAGS = SmileGenerator.Feature.collectDefaults();
    }

    public SmileFactory() {
        this(null);
    }

    public SmileFactory(ObjectCodec objectCodec) {
        super(objectCodec);
        this._smileParserFeatures = DEFAULT_SMILE_PARSER_FEATURE_FLAGS;
        this._smileGeneratorFeatures = DEFAULT_SMILE_GENERATOR_FEATURE_FLAGS;
    }

    protected JsonGenerator _createJsonGenerator(Writer writer, IOContext iOContext) {
        if (this._cfgDelegateToTextual) {
            return super._createJsonGenerator(writer, iOContext);
        }
        throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
    }

    protected SmileGenerator _createJsonGenerator(OutputStream outputStream, IOContext iOContext) {
        int i = this._smileGeneratorFeatures;
        SmileGenerator smileGenerator = new SmileGenerator(iOContext, this._generatorFeatures, i, this._objectCodec, outputStream);
        if ((SmileGenerator.Feature.WRITE_HEADER.getMask() & i) != 0) {
            smileGenerator.writeHeader();
        } else if ((SmileGenerator.Feature.CHECK_SHARED_STRING_VALUES.getMask() & i) != 0) {
            throw new JsonGenerationException("Inconsistent settings: WRITE_HEADER disabled, but CHECK_SHARED_STRING_VALUES enabled; can not construct generator due to possible data loss (either enable WRITE_HEADER, or disable CHECK_SHARED_STRING_VALUES to resolve)");
        } else if ((SmileGenerator.Feature.ENCODE_BINARY_AS_7BIT.getMask() & i) == 0) {
            throw new JsonGenerationException("Inconsistent settings: WRITE_HEADER disabled, but ENCODE_BINARY_AS_7BIT disabled; can not construct generator due to possible data loss (either enable WRITE_HEADER, or ENCODE_BINARY_AS_7BIT to resolve)");
        }
        return smileGenerator;
    }

    protected JsonParser _createJsonParser(Reader reader, IOContext iOContext) {
        if (this._cfgDelegateToTextual) {
            return super._createJsonParser(reader, iOContext);
        }
        throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
    }

    protected SmileParser _createJsonParser(InputStream inputStream, IOContext iOContext) {
        return new SmileParserBootstrapper(iOContext, inputStream).constructParser(this._parserFeatures, this._smileParserFeatures, this._objectCodec, this._rootByteSymbols);
    }

    protected SmileParser _createJsonParser(byte[] bArr, int i, int i2, IOContext iOContext) {
        return new SmileParserBootstrapper(iOContext, bArr, i, i2).constructParser(this._parserFeatures, this._smileParserFeatures, this._objectCodec, this._rootByteSymbols);
    }

    protected Writer _createWriter(OutputStream outputStream, JsonEncoding jsonEncoding, IOContext iOContext) {
        if (this._cfgDelegateToTextual) {
            return super._createWriter(outputStream, jsonEncoding, iOContext);
        }
        throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
    }

    public final SmileFactory configure(SmileGenerator.Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public final SmileFactory configure(Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public SmileGenerator createJsonGenerator(OutputStream outputStream) {
        return _createJsonGenerator(outputStream, _createContext(outputStream, false));
    }

    public SmileGenerator createJsonGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) {
        return createJsonGenerator(outputStream);
    }

    public SmileParser createJsonParser(File file) {
        return _createJsonParser(new FileInputStream(file), _createContext(file, true));
    }

    public SmileParser createJsonParser(InputStream inputStream) {
        return _createJsonParser(inputStream, _createContext(inputStream, false));
    }

    public SmileParser createJsonParser(URL url) {
        return _createJsonParser(_optimizedStreamFromURL(url), _createContext(url, true));
    }

    public SmileParser createJsonParser(byte[] bArr) {
        return _createJsonParser(bArr, (int) DEFAULT_SMILE_PARSER_FEATURE_FLAGS, bArr.length, _createContext(bArr, true));
    }

    public SmileParser createJsonParser(byte[] bArr, int i, int i2) {
        return _createJsonParser(bArr, i, i2, _createContext(bArr, true));
    }

    public void delegateToTextual(boolean z) {
        this._cfgDelegateToTextual = z;
    }

    public SmileFactory disable(SmileGenerator.Feature feature) {
        this._smileGeneratorFeatures &= feature.getMask() ^ -1;
        return this;
    }

    public SmileFactory disable(Feature feature) {
        this._smileParserFeatures &= feature.getMask() ^ -1;
        return this;
    }

    public SmileFactory enable(SmileGenerator.Feature feature) {
        this._smileGeneratorFeatures |= feature.getMask();
        return this;
    }

    public SmileFactory enable(Feature feature) {
        this._smileParserFeatures |= feature.getMask();
        return this;
    }

    public String getFormatName() {
        return FORMAT_NAME_SMILE;
    }

    public MatchStrength hasFormat(InputAccessor inputAccessor) {
        return SmileParserBootstrapper.hasSmileFormat(inputAccessor);
    }

    public final boolean isEnabled(SmileGenerator.Feature feature) {
        return (this._smileGeneratorFeatures & feature.getMask()) != 0;
    }

    public final boolean isEnabled(Feature feature) {
        return (this._smileParserFeatures & feature.getMask()) != 0;
    }
}
