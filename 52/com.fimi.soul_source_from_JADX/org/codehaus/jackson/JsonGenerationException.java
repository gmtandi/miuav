package org.codehaus.jackson;

public class JsonGenerationException extends JsonProcessingException {
    static final long serialVersionUID = 123;

    public JsonGenerationException(String str) {
        super(str, (JsonLocation) null);
    }

    public JsonGenerationException(String str, Throwable th) {
        super(str, (JsonLocation) null, th);
    }

    public JsonGenerationException(Throwable th) {
        super(th);
    }
}
