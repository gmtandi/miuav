package org.codehaus.jackson;

import com.fimi.soul.module.setting.newhand.C1873o;
import java.io.IOException;

public class JsonProcessingException extends IOException {
    static final long serialVersionUID = 123;
    protected JsonLocation mLocation;

    protected JsonProcessingException(String str) {
        super(str);
    }

    protected JsonProcessingException(String str, Throwable th) {
        this(str, null, th);
    }

    protected JsonProcessingException(String str, JsonLocation jsonLocation) {
        this(str, jsonLocation, null);
    }

    protected JsonProcessingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this.mLocation = jsonLocation;
    }

    protected JsonProcessingException(Throwable th) {
        this(null, null, th);
    }

    public JsonLocation getLocation() {
        return this.mLocation;
    }

    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = C1873o.an;
        }
        JsonLocation location = getLocation();
        if (location == null) {
            return message;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message);
        stringBuilder.append('\n');
        stringBuilder.append(" at ");
        stringBuilder.append(location.toString());
        return stringBuilder.toString();
    }

    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }
}
