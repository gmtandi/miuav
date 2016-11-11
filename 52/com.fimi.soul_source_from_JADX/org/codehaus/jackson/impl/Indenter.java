package org.codehaus.jackson.impl;

import org.codehaus.jackson.JsonGenerator;

public interface Indenter {
    boolean isInline();

    void writeIndentation(JsonGenerator jsonGenerator, int i);
}
