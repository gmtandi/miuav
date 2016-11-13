package org.codehaus.jackson.map.ser.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.BeanPropertyFilter;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;

public abstract class SimpleBeanPropertyFilter implements BeanPropertyFilter {

    public class FilterExceptFilter extends SimpleBeanPropertyFilter {
        protected final Set<String> _propertiesToInclude;

        public FilterExceptFilter(Set<String> set) {
            this._propertiesToInclude = set;
        }

        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) {
            if (this._propertiesToInclude.contains(beanPropertyWriter.getName())) {
                beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
            }
        }
    }

    public class SerializeExceptFilter extends SimpleBeanPropertyFilter {
        protected final Set<String> _propertiesToExclude;

        public SerializeExceptFilter(Set<String> set) {
            this._propertiesToExclude = set;
        }

        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) {
            if (!this._propertiesToExclude.contains(beanPropertyWriter.getName())) {
                beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
            }
        }
    }

    protected SimpleBeanPropertyFilter() {
    }

    public static SimpleBeanPropertyFilter filterOutAllExcept(Set<String> set) {
        return new FilterExceptFilter(set);
    }

    public static SimpleBeanPropertyFilter filterOutAllExcept(String... strArr) {
        Object hashSet = new HashSet(strArr.length);
        Collections.addAll(hashSet, strArr);
        return new FilterExceptFilter(hashSet);
    }

    public static SimpleBeanPropertyFilter serializeAllExcept(Set<String> set) {
        return new SerializeExceptFilter(set);
    }

    public static SimpleBeanPropertyFilter serializeAllExcept(String... strArr) {
        Object hashSet = new HashSet(strArr.length);
        Collections.addAll(hashSet, strArr);
        return new SerializeExceptFilter(hashSet);
    }
}
