package org.codehaus.jackson.map;

public class RuntimeJsonMappingException extends RuntimeException {
    public RuntimeJsonMappingException(String str) {
        super(str);
    }

    public RuntimeJsonMappingException(String str, JsonMappingException jsonMappingException) {
        super(str, jsonMappingException);
    }

    public RuntimeJsonMappingException(JsonMappingException jsonMappingException) {
        super(jsonMappingException);
    }
}
