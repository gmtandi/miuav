package org.codehaus.jackson.io;

import java.io.OutputStream;
import java.io.Writer;

public abstract class OutputDecorator {
    public abstract OutputStream decorate(IOContext iOContext, OutputStream outputStream);

    public abstract Writer decorate(IOContext iOContext, Writer writer);
}
