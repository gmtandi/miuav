package org.codehaus.jackson.xc;

import com.baidu.tts.loopj.RequestParams;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.StdScalarDeserializer;

public class DataHandlerJsonDeserializer extends StdScalarDeserializer<DataHandler> {

    /* renamed from: org.codehaus.jackson.xc.DataHandlerJsonDeserializer.1 */
    class C35951 implements DataSource {
        final /* synthetic */ byte[] val$value;

        C35951(byte[] bArr) {
            this.val$value = bArr;
        }

        public String getContentType() {
            return RequestParams.APPLICATION_OCTET_STREAM;
        }

        public InputStream getInputStream() {
            return new ByteArrayInputStream(this.val$value);
        }

        public String getName() {
            return "json-binary-data";
        }

        public OutputStream getOutputStream() {
            throw new IOException();
        }
    }

    public DataHandlerJsonDeserializer() {
        super(DataHandler.class);
    }

    public DataHandler deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return new DataHandler(new C35951(jsonParser.getBinaryValue()));
    }
}
