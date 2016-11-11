package org.codehaus.jackson.map.jsontype.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;

public class StdSubtypeResolver extends SubtypeResolver {
    protected LinkedHashSet<NamedType> _registeredSubtypes;

    protected void _collectAndResolve(AnnotatedClass annotatedClass, NamedType namedType, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector, HashMap<NamedType, NamedType> hashMap) {
        if (!namedType.hasName()) {
            String findTypeName = annotationIntrospector.findTypeName(annotatedClass);
            if (findTypeName != null) {
                namedType = new NamedType(namedType.getType(), findTypeName);
            }
        }
        if (!hashMap.containsKey(namedType)) {
            hashMap.put(namedType, namedType);
            Collection<NamedType> findSubtypes = annotationIntrospector.findSubtypes(annotatedClass);
            if (findSubtypes != null && !findSubtypes.isEmpty()) {
                for (NamedType namedType2 : findSubtypes) {
                    AnnotatedClass constructWithoutSuperTypes = AnnotatedClass.constructWithoutSuperTypes(namedType2.getType(), annotationIntrospector, mapperConfig);
                    _collectAndResolve(constructWithoutSuperTypes, !namedType2.hasName() ? new NamedType(namedType2.getType(), annotationIntrospector.findTypeName(constructWithoutSuperTypes)) : namedType2, mapperConfig, annotationIntrospector, hashMap);
                }
            }
        } else if (namedType.hasName() && !((NamedType) hashMap.get(namedType)).hasName()) {
            hashMap.put(namedType, namedType);
        }
    }

    public Collection<NamedType> collectAndResolveSubtypes(AnnotatedClass annotatedClass, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector) {
        HashMap hashMap = new HashMap();
        if (this._registeredSubtypes != null) {
            Class rawType = annotatedClass.getRawType();
            Iterator it = this._registeredSubtypes.iterator();
            while (it.hasNext()) {
                NamedType namedType = (NamedType) it.next();
                if (rawType.isAssignableFrom(namedType.getType())) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType.getType(), annotationIntrospector, mapperConfig), namedType, mapperConfig, annotationIntrospector, hashMap);
                }
            }
        }
        _collectAndResolve(annotatedClass, new NamedType(annotatedClass.getRawType(), null), mapperConfig, annotationIntrospector, hashMap);
        return new ArrayList(hashMap.values());
    }

    public Collection<NamedType> collectAndResolveSubtypes(AnnotatedMember annotatedMember, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector) {
        NamedType namedType;
        HashMap hashMap = new HashMap();
        if (this._registeredSubtypes != null) {
            Class rawType = annotatedMember.getRawType();
            Iterator it = this._registeredSubtypes.iterator();
            while (it.hasNext()) {
                namedType = (NamedType) it.next();
                if (rawType.isAssignableFrom(namedType.getType())) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType.getType(), annotationIntrospector, mapperConfig), namedType, mapperConfig, annotationIntrospector, hashMap);
                }
            }
        }
        Collection<NamedType> findSubtypes = annotationIntrospector.findSubtypes(annotatedMember);
        if (findSubtypes != null) {
            for (NamedType namedType2 : findSubtypes) {
                _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType2.getType(), annotationIntrospector, mapperConfig), namedType2, mapperConfig, annotationIntrospector, hashMap);
            }
        }
        namedType2 = new NamedType(annotatedMember.getRawType(), null);
        _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(annotatedMember.getRawType(), annotationIntrospector, mapperConfig), namedType2, mapperConfig, annotationIntrospector, hashMap);
        return new ArrayList(hashMap.values());
    }

    public void registerSubtypes(Class<?>... clsArr) {
        NamedType[] namedTypeArr = new NamedType[clsArr.length];
        int length = clsArr.length;
        for (int i = 0; i < length; i++) {
            namedTypeArr[i] = new NamedType(clsArr[i]);
        }
        registerSubtypes(namedTypeArr);
    }

    public void registerSubtypes(NamedType... namedTypeArr) {
        if (this._registeredSubtypes == null) {
            this._registeredSubtypes = new LinkedHashSet();
        }
        for (Object add : namedTypeArr) {
            this._registeredSubtypes.add(add);
        }
    }
}
