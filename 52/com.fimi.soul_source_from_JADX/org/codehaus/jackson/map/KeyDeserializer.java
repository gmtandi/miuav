package org.codehaus.jackson.map;

public abstract class KeyDeserializer {

    public abstract class None extends KeyDeserializer {
    }

    public abstract Object deserializeKey(String str, DeserializationContext deserializationContext);
}
