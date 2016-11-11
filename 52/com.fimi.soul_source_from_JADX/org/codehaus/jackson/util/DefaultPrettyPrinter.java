package org.codehaus.jackson.util;

import java.util.Arrays;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.PrettyPrinter;
import org.codehaus.jackson.impl.Indenter;
import org.p122a.p123a.p124f.p125c.C3022o;

public class DefaultPrettyPrinter implements PrettyPrinter {
    protected Indenter _arrayIndenter;
    protected int _nesting;
    protected Indenter _objectIndenter;
    protected boolean _spacesInObjectEntries;

    public class FixedSpaceIndenter implements Indenter {
        public boolean isInline() {
            return true;
        }

        public void writeIndentation(JsonGenerator jsonGenerator, int i) {
            jsonGenerator.writeRaw((char) C3022o.f15055c);
        }
    }

    public class Lf2SpacesIndenter implements Indenter {
        static final char[] SPACES;
        static final int SPACE_COUNT = 64;
        static final String SYSTEM_LINE_SEPARATOR;

        static {
            String str = null;
            try {
                str = System.getProperty("line.separator");
            } catch (Throwable th) {
            }
            if (str == null) {
                str = "\n";
            }
            SYSTEM_LINE_SEPARATOR = str;
            SPACES = new char[SPACE_COUNT];
            Arrays.fill(SPACES, C3022o.f15055c);
        }

        public boolean isInline() {
            return false;
        }

        public void writeIndentation(JsonGenerator jsonGenerator, int i) {
            jsonGenerator.writeRaw(SYSTEM_LINE_SEPARATOR);
            int i2 = i + i;
            while (i2 > SPACE_COUNT) {
                jsonGenerator.writeRaw(SPACES, 0, (int) SPACE_COUNT);
                i2 -= SPACES.length;
            }
            jsonGenerator.writeRaw(SPACES, 0, i2);
        }
    }

    public class NopIndenter implements Indenter {
        public boolean isInline() {
            return true;
        }

        public void writeIndentation(JsonGenerator jsonGenerator, int i) {
        }
    }

    public DefaultPrettyPrinter() {
        this._arrayIndenter = new FixedSpaceIndenter();
        this._objectIndenter = new Lf2SpacesIndenter();
        this._spacesInObjectEntries = true;
        this._nesting = 0;
    }

    public void beforeArrayValues(JsonGenerator jsonGenerator) {
        this._arrayIndenter.writeIndentation(jsonGenerator, this._nesting);
    }

    public void beforeObjectEntries(JsonGenerator jsonGenerator) {
        this._objectIndenter.writeIndentation(jsonGenerator, this._nesting);
    }

    public void indentArraysWith(Indenter indenter) {
        if (indenter == null) {
            indenter = new NopIndenter();
        }
        this._arrayIndenter = indenter;
    }

    public void indentObjectsWith(Indenter indenter) {
        if (indenter == null) {
            indenter = new NopIndenter();
        }
        this._objectIndenter = indenter;
    }

    public void spacesInObjectEntries(boolean z) {
        this._spacesInObjectEntries = z;
    }

    public void writeArrayValueSeparator(JsonGenerator jsonGenerator) {
        jsonGenerator.writeRaw(',');
        this._arrayIndenter.writeIndentation(jsonGenerator, this._nesting);
    }

    public void writeEndArray(JsonGenerator jsonGenerator, int i) {
        if (!this._arrayIndenter.isInline()) {
            this._nesting--;
        }
        if (i > 0) {
            this._arrayIndenter.writeIndentation(jsonGenerator, this._nesting);
        } else {
            jsonGenerator.writeRaw((char) C3022o.f15055c);
        }
        jsonGenerator.writeRaw(']');
    }

    public void writeEndObject(JsonGenerator jsonGenerator, int i) {
        if (!this._objectIndenter.isInline()) {
            this._nesting--;
        }
        if (i > 0) {
            this._objectIndenter.writeIndentation(jsonGenerator, this._nesting);
        } else {
            jsonGenerator.writeRaw((char) C3022o.f15055c);
        }
        jsonGenerator.writeRaw('}');
    }

    public void writeObjectEntrySeparator(JsonGenerator jsonGenerator) {
        jsonGenerator.writeRaw(',');
        this._objectIndenter.writeIndentation(jsonGenerator, this._nesting);
    }

    public void writeObjectFieldValueSeparator(JsonGenerator jsonGenerator) {
        if (this._spacesInObjectEntries) {
            jsonGenerator.writeRaw(" : ");
        } else {
            jsonGenerator.writeRaw(':');
        }
    }

    public void writeRootValueSeparator(JsonGenerator jsonGenerator) {
        jsonGenerator.writeRaw((char) C3022o.f15055c);
    }

    public void writeStartArray(JsonGenerator jsonGenerator) {
        if (!this._arrayIndenter.isInline()) {
            this._nesting++;
        }
        jsonGenerator.writeRaw('[');
    }

    public void writeStartObject(JsonGenerator jsonGenerator) {
        jsonGenerator.writeRaw('{');
        if (!this._objectIndenter.isInline()) {
            this._nesting++;
        }
    }
}
