package org.codehaus.jackson.xc;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import javax.activation.DataHandler;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class DataHandlerJsonSerializer extends SerializerBase<DataHandler> {
    public DataHandlerJsonSerializer() {
        super(DataHandler.class);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        JsonNode createSchemaNode = createSchemaNode("array", true);
        createSchemaNode.put("items", createSchemaNode("string"));
        return createSchemaNode;
    }

    public void serialize(DataHandler dataHandler, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[Opcodes.ACC_SYNTHETIC];
        InputStream inputStream = dataHandler.getInputStream();
        for (int read = inputStream.read(bArr); read > 0; read = inputStream.read(bArr)) {
            byteArrayOutputStream.write(bArr, 0, read);
        }
        jsonGenerator.writeBinary(byteArrayOutputStream.toByteArray());
    }
}
