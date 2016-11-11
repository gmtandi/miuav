package org.codehaus.jackson.map.ext;

import java.util.Collection;
import java.util.Map.Entry;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.deser.StdDeserializer;
import org.codehaus.jackson.map.util.Provider;
import org.codehaus.jackson.type.JavaType;

public class OptionalHandlerFactory {
    private static final String CLASS_NAME_DOM_DOCUMENT = "org.w3c.dom.Node";
    private static final String CLASS_NAME_DOM_NODE = "org.w3c.dom.Node";
    private static final String DESERIALIZERS_FOR_JAVAX_XML = "org.codehaus.jackson.map.ext.CoreXMLDeserializers";
    private static final String DESERIALIZERS_FOR_JODA_DATETIME = "org.codehaus.jackson.map.ext.JodaDeserializers";
    private static final String DESERIALIZER_FOR_DOM_DOCUMENT = "org.codehaus.jackson.map.ext.DOMDeserializer$DocumentDeserializer";
    private static final String DESERIALIZER_FOR_DOM_NODE = "org.codehaus.jackson.map.ext.DOMDeserializer$NodeDeserializer";
    private static final String PACKAGE_PREFIX_JAVAX_XML = "javax.xml.";
    private static final String PACKAGE_PREFIX_JODA_DATETIME = "org.joda.time.";
    private static final String SERIALIZERS_FOR_JAVAX_XML = "org.codehaus.jackson.map.ext.CoreXMLSerializers";
    private static final String SERIALIZERS_FOR_JODA_DATETIME = "org.codehaus.jackson.map.ext.JodaSerializers";
    private static final String SERIALIZER_FOR_DOM_NODE = "org.codehaus.jackson.map.ext.DOMSerializer";
    public static final OptionalHandlerFactory instance;

    static {
        instance = new OptionalHandlerFactory();
    }

    protected OptionalHandlerFactory() {
    }

    private boolean doesImplement(Class<?> cls, String str) {
        Class superclass;
        while (superclass != null) {
            if (superclass.getName().equals(str) || hasInterface(superclass, str)) {
                return true;
            }
            superclass = superclass.getSuperclass();
        }
        return false;
    }

    private boolean hasInterface(Class<?> cls, String str) {
        Class[] interfaces = cls.getInterfaces();
        for (Class name : interfaces) {
            if (name.getName().equals(str)) {
                return true;
            }
        }
        for (Class name2 : interfaces) {
            if (hasInterface(name2, str)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasInterfaceStartingWith(Class<?> cls, String str) {
        Class[] interfaces = cls.getInterfaces();
        for (Class name : interfaces) {
            if (name.getName().startsWith(str)) {
                return true;
            }
        }
        for (Class name2 : interfaces) {
            if (hasInterfaceStartingWith(name2, str)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSupertypeStartingWith(Class<?> cls, String str) {
        for (Class superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            if (superclass.getName().startsWith(str)) {
                return true;
            }
        }
        Class superclass2;
        while (superclass2 != null) {
            if (hasInterfaceStartingWith(superclass2, str)) {
                return true;
            }
            superclass2 = superclass2.getSuperclass();
        }
        return false;
    }

    private Object instantiate(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (LinkageError e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public JsonDeserializer<?> findDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) {
        Class rawClass = javaType.getRawClass();
        String name = rawClass.getName();
        if (name.startsWith(PACKAGE_PREFIX_JODA_DATETIME)) {
            name = DESERIALIZERS_FOR_JODA_DATETIME;
        } else if (!name.startsWith(PACKAGE_PREFIX_JAVAX_XML) && !hasSupertypeStartingWith(rawClass, PACKAGE_PREFIX_JAVAX_XML)) {
            return doesImplement(rawClass, CLASS_NAME_DOM_NODE) ? (JsonDeserializer) instantiate(DESERIALIZER_FOR_DOM_DOCUMENT) : doesImplement(rawClass, CLASS_NAME_DOM_NODE) ? (JsonDeserializer) instantiate(DESERIALIZER_FOR_DOM_NODE) : null;
        } else {
            name = DESERIALIZERS_FOR_JAVAX_XML;
        }
        Object instantiate = instantiate(name);
        if (instantiate == null) {
            return null;
        }
        Collection<StdDeserializer> provide = ((Provider) instantiate).provide();
        for (StdDeserializer stdDeserializer : provide) {
            if (rawClass == stdDeserializer.getValueClass()) {
                return stdDeserializer;
            }
        }
        for (StdDeserializer stdDeserializer2 : provide) {
            if (stdDeserializer2.getValueClass().isAssignableFrom(rawClass)) {
                return stdDeserializer2;
            }
        }
        return null;
    }

    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType) {
        Class rawClass = javaType.getRawClass();
        String name = rawClass.getName();
        if (name.startsWith(PACKAGE_PREFIX_JODA_DATETIME)) {
            name = SERIALIZERS_FOR_JODA_DATETIME;
        } else if (!name.startsWith(PACKAGE_PREFIX_JAVAX_XML) && !hasSupertypeStartingWith(rawClass, PACKAGE_PREFIX_JAVAX_XML)) {
            return doesImplement(rawClass, CLASS_NAME_DOM_NODE) ? (JsonSerializer) instantiate(SERIALIZER_FOR_DOM_NODE) : null;
        } else {
            name = SERIALIZERS_FOR_JAVAX_XML;
        }
        Object instantiate = instantiate(name);
        if (instantiate == null) {
            return null;
        }
        Collection<Entry> provide = ((Provider) instantiate).provide();
        for (Entry entry : provide) {
            if (rawClass == entry.getKey()) {
                return (JsonSerializer) entry.getValue();
            }
        }
        for (Entry entry2 : provide) {
            if (((Class) entry2.getKey()).isAssignableFrom(rawClass)) {
                return (JsonSerializer) entry2.getValue();
            }
        }
        return null;
    }
}
