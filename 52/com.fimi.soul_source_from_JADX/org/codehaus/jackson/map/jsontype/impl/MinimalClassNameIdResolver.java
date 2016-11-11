package org.codehaus.jackson.map.jsontype.impl;

import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.p122a.p123a.C2915a;

public class MinimalClassNameIdResolver extends ClassNameIdResolver {
    protected final String _basePackageName;
    protected final String _basePackagePrefix;

    protected MinimalClassNameIdResolver(JavaType javaType, TypeFactory typeFactory) {
        super(javaType, typeFactory);
        String name = javaType.getRawClass().getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf < 0) {
            this._basePackageName = C2915a.f14760f;
            this._basePackagePrefix = ".";
            return;
        }
        this._basePackagePrefix = name.substring(0, lastIndexOf + 1);
        this._basePackageName = name.substring(0, lastIndexOf);
    }

    public Id getMechanism() {
        return Id.MINIMAL_CLASS;
    }

    public String idFromValue(Object obj) {
        String name = obj.getClass().getName();
        return name.startsWith(this._basePackagePrefix) ? name.substring(this._basePackagePrefix.length() - 1) : name;
    }

    public JavaType typeFromId(String str) {
        if (str.startsWith(".")) {
            StringBuilder stringBuilder = new StringBuilder(str.length() + this._basePackageName.length());
            if (this._basePackageName.length() == 0) {
                stringBuilder.append(str.substring(1));
            } else {
                stringBuilder.append(this._basePackageName).append(str);
            }
            str = stringBuilder.toString();
        }
        return super.typeFromId(str);
    }
}
