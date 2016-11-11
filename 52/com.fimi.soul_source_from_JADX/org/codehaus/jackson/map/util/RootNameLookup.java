package org.codehaus.jackson.map.util;

import it.p074a.p075a.C2799f;
import org.codehaus.jackson.io.SerializedString;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.type.JavaType;

public class RootNameLookup {
    protected LRUMap<ClassKey, SerializedString> _rootNames;

    public synchronized SerializedString findRootName(Class<?> cls, MapperConfig<?> mapperConfig) {
        SerializedString serializedString;
        ClassKey classKey = new ClassKey(cls);
        if (this._rootNames == null) {
            this._rootNames = new LRUMap(20, C2799f.f14282t);
        } else {
            serializedString = (SerializedString) this._rootNames.get(classKey);
            if (serializedString != null) {
            }
        }
        String findRootName = mapperConfig.getAnnotationIntrospector().findRootName(((BasicBeanDescription) mapperConfig.introspectClassAnnotations(cls)).getClassInfo());
        serializedString = new SerializedString(findRootName == null ? cls.getSimpleName() : findRootName);
        this._rootNames.put(classKey, serializedString);
        return serializedString;
    }

    public SerializedString findRootName(JavaType javaType, MapperConfig<?> mapperConfig) {
        return findRootName(javaType.getRawClass(), (MapperConfig) mapperConfig);
    }
}
