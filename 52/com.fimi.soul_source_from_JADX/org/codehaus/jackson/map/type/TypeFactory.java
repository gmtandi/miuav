package org.codehaus.jackson.map.type;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

public final class TypeFactory {
    private static final JavaType[] NO_TYPES;
    @Deprecated
    public static final TypeFactory instance;
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;

    static {
        instance = new TypeFactory();
        NO_TYPES = new JavaType[0];
    }

    private TypeFactory() {
        this._parser = new TypeParser(this);
        this._modifiers = null;
    }

    protected TypeFactory(TypeParser typeParser, TypeModifier[] typeModifierArr) {
        this._parser = typeParser;
        this._modifiers = typeModifierArr;
    }

    private JavaType _collectionType(Class<?> cls) {
        JavaType[] findTypeParameters = findTypeParameters((Class) cls, Collection.class);
        if (findTypeParameters == null) {
            return CollectionType.construct(cls, _unknownType());
        }
        if (findTypeParameters.length == 1) {
            return CollectionType.construct(cls, findTypeParameters[0]);
        }
        throw new IllegalArgumentException("Strange Collection type " + cls.getName() + ": can not determine type parameters");
    }

    protected static HierarchicType _findSuperClassChain(Type type, Class<?> cls) {
        HierarchicType hierarchicType = new HierarchicType(type);
        Class<?> rawClass = hierarchicType.getRawClass();
        if (rawClass == cls) {
            return hierarchicType;
        }
        Type genericSuperclass = rawClass.getGenericSuperclass();
        if (genericSuperclass != null) {
            HierarchicType _findSuperClassChain = _findSuperClassChain(genericSuperclass, cls);
            if (_findSuperClassChain != null) {
                _findSuperClassChain.setSubType(hierarchicType);
                hierarchicType.setSuperType(_findSuperClassChain);
                return hierarchicType;
            }
        }
        return null;
    }

    protected static HierarchicType _findSuperInterfaceChain(Type type, Class<?> cls) {
        HierarchicType hierarchicType = new HierarchicType(type);
        Class<?> rawClass = hierarchicType.getRawClass();
        if (rawClass == cls) {
            return hierarchicType;
        }
        Type[] genericInterfaces = rawClass.getGenericInterfaces();
        if (genericInterfaces != null) {
            for (Type _findSuperInterfaceChain : genericInterfaces) {
                HierarchicType _findSuperInterfaceChain2 = _findSuperInterfaceChain(_findSuperInterfaceChain, cls);
                if (_findSuperInterfaceChain2 != null) {
                    _findSuperInterfaceChain2.setSubType(hierarchicType);
                    hierarchicType.setSuperType(_findSuperInterfaceChain2);
                    return hierarchicType;
                }
            }
        }
        Type genericSuperclass = rawClass.getGenericSuperclass();
        if (genericSuperclass != null) {
            HierarchicType _findSuperInterfaceChain3 = _findSuperInterfaceChain(genericSuperclass, cls);
            if (_findSuperInterfaceChain3 != null) {
                _findSuperInterfaceChain3.setSubType(hierarchicType);
                hierarchicType.setSuperType(_findSuperInterfaceChain3);
                return hierarchicType;
            }
        }
        return null;
    }

    protected static HierarchicType _findSuperTypeChain(Class<?> cls, Class<?> cls2) {
        return cls2.isInterface() ? _findSuperInterfaceChain(cls, cls2) : _findSuperClassChain(cls, cls2);
    }

    private JavaType _mapType(Class<?> cls) {
        JavaType[] findTypeParameters = findTypeParameters((Class) cls, Map.class);
        if (findTypeParameters == null) {
            return MapType.construct(cls, _unknownType(), _unknownType());
        }
        if (findTypeParameters.length == 2) {
            return MapType.construct(cls, findTypeParameters[0], findTypeParameters[1]);
        }
        throw new IllegalArgumentException("Strange Map type " + cls.getName() + ": can not determine type parameters");
    }

    @Deprecated
    public static JavaType arrayType(Class<?> cls) {
        return instance.constructArrayType(instance.constructType((Type) cls));
    }

    @Deprecated
    public static JavaType arrayType(JavaType javaType) {
        return instance.constructArrayType(javaType);
    }

    @Deprecated
    public static JavaType collectionType(Class<? extends Collection> cls, Class<?> cls2) {
        return instance.constructCollectionType((Class) cls, instance.constructType((Type) cls2));
    }

    @Deprecated
    public static JavaType collectionType(Class<? extends Collection> cls, JavaType javaType) {
        return instance.constructCollectionType((Class) cls, javaType);
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    @Deprecated
    public static JavaType fastSimpleType(Class<?> cls) {
        return instance.uncheckedSimpleType(cls);
    }

    @Deprecated
    public static JavaType[] findParameterTypes(Class<?> cls, Class<?> cls2) {
        return instance.findTypeParameters((Class) cls, (Class) cls2);
    }

    @Deprecated
    public static JavaType[] findParameterTypes(Class<?> cls, Class<?> cls2, TypeBindings typeBindings) {
        return instance.findTypeParameters(cls, cls2, typeBindings);
    }

    @Deprecated
    public static JavaType[] findParameterTypes(JavaType javaType, Class<?> cls) {
        return instance.findTypeParameters(javaType, (Class) cls);
    }

    public static JavaType fromCanonical(String str) {
        return instance.constructFromCanonical(str);
    }

    @Deprecated
    public static JavaType fromClass(Class<?> cls) {
        return instance._fromClass(cls, null);
    }

    @Deprecated
    public static JavaType fromType(Type type) {
        return instance._constructType(type, null);
    }

    @Deprecated
    public static JavaType fromTypeReference(TypeReference<?> typeReference) {
        return type(typeReference.getType());
    }

    @Deprecated
    public static JavaType mapType(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        return instance.constructMapType((Class) cls, type((Type) cls2), instance.constructType((Type) cls3));
    }

    @Deprecated
    public static JavaType mapType(Class<? extends Map> cls, JavaType javaType, JavaType javaType2) {
        return instance.constructMapType((Class) cls, javaType, javaType2);
    }

    @Deprecated
    public static JavaType parametricType(Class<?> cls, Class<?>... clsArr) {
        return instance.constructParametricType((Class) cls, (Class[]) clsArr);
    }

    @Deprecated
    public static JavaType parametricType(Class<?> cls, JavaType... javaTypeArr) {
        return instance.constructParametricType((Class) cls, javaTypeArr);
    }

    public static Class<?> rawClass(Type type) {
        return type instanceof Class ? (Class) type : defaultInstance().constructType(type).getRawClass();
    }

    @Deprecated
    public static JavaType specialize(JavaType javaType, Class<?> cls) {
        return instance.constructSpecializedType(javaType, cls);
    }

    @Deprecated
    public static JavaType type(Type type) {
        return instance._constructType(type, null);
    }

    @Deprecated
    public static JavaType type(Type type, Class<?> cls) {
        return instance.constructType(type, (Class) cls);
    }

    @Deprecated
    public static JavaType type(Type type, TypeBindings typeBindings) {
        return instance._constructType(type, typeBindings);
    }

    @Deprecated
    public static JavaType type(Type type, JavaType javaType) {
        return instance.constructType(type, javaType);
    }

    @Deprecated
    public static JavaType type(TypeReference<?> typeReference) {
        return instance.constructType(typeReference.getType());
    }

    public static JavaType unknownType() {
        return defaultInstance()._unknownType();
    }

    public JavaType _constructType(Type type, TypeBindings typeBindings) {
        JavaType _fromClass;
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (typeBindings == null) {
                typeBindings = new TypeBindings(this, cls);
            }
            _fromClass = _fromClass(cls, typeBindings);
        } else if (type instanceof ParameterizedType) {
            _fromClass = _fromParamType((ParameterizedType) type, typeBindings);
        } else if (type instanceof GenericArrayType) {
            _fromClass = _fromArrayType((GenericArrayType) type, typeBindings);
        } else if (type instanceof TypeVariable) {
            _fromClass = _fromVariable((TypeVariable) type, typeBindings);
        } else if (type instanceof WildcardType) {
            _fromClass = _fromWildcard((WildcardType) type, typeBindings);
        } else {
            throw new IllegalArgumentException("Unrecognized Type: " + type.toString());
        }
        if (!(this._modifiers == null || r0.isContainerType())) {
            TypeModifier[] typeModifierArr = this._modifiers;
            int length = typeModifierArr.length;
            int i = 0;
            while (i < length) {
                JavaType modifyType = typeModifierArr[i].modifyType(_fromClass, type, typeBindings, this);
                i++;
                _fromClass = modifyType;
            }
        }
        return _fromClass;
    }

    protected JavaType _fromArrayType(GenericArrayType genericArrayType, TypeBindings typeBindings) {
        return ArrayType.construct(_constructType(genericArrayType.getGenericComponentType(), typeBindings));
    }

    protected JavaType _fromClass(Class<?> cls, TypeBindings typeBindings) {
        return cls.isArray() ? ArrayType.construct(_constructType(cls.getComponentType(), null)) : cls.isEnum() ? new SimpleType(cls) : Map.class.isAssignableFrom(cls) ? _mapType(cls) : Collection.class.isAssignableFrom(cls) ? _collectionType(cls) : new SimpleType(cls);
    }

    protected JavaType _fromParamType(ParameterizedType parameterizedType, TypeBindings typeBindings) {
        JavaType[] javaTypeArr;
        Class cls = (Class) parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int length = actualTypeArguments == null ? 0 : actualTypeArguments.length;
        if (length == 0) {
            javaTypeArr = NO_TYPES;
        } else {
            javaTypeArr = new JavaType[length];
            for (int i = 0; i < length; i++) {
                javaTypeArr[i] = _constructType(actualTypeArguments[i], typeBindings);
            }
        }
        if (Map.class.isAssignableFrom(cls)) {
            javaTypeArr = findTypeParameters(constructSimpleType(cls, javaTypeArr), Map.class);
            if (javaTypeArr.length == 2) {
                return MapType.construct(cls, javaTypeArr[0], javaTypeArr[1]);
            }
            throw new IllegalArgumentException("Could not find 2 type parameters for Map class " + cls.getName() + " (found " + javaTypeArr.length + ")");
        } else if (!Collection.class.isAssignableFrom(cls)) {
            return length == 0 ? new SimpleType(cls) : constructSimpleType(cls, javaTypeArr);
        } else {
            javaTypeArr = findTypeParameters(constructSimpleType(cls, javaTypeArr), Collection.class);
            if (javaTypeArr.length == 1) {
                return CollectionType.construct(cls, javaTypeArr[0]);
            }
            throw new IllegalArgumentException("Could not find 1 type parameter for Collection class " + cls.getName() + " (found " + javaTypeArr.length + ")");
        }
    }

    protected JavaType _fromParameterizedClass(Class<?> cls, List<JavaType> list) {
        if (cls.isArray()) {
            return ArrayType.construct(_constructType(cls.getComponentType(), null));
        }
        if (cls.isEnum()) {
            return new SimpleType(cls);
        }
        if (!Map.class.isAssignableFrom(cls)) {
            return Collection.class.isAssignableFrom(cls) ? list.size() >= 1 ? CollectionType.construct(cls, (JavaType) list.get(0)) : _collectionType(cls) : list.size() == 0 ? new SimpleType(cls) : constructSimpleType(cls, (JavaType[]) list.toArray(new JavaType[list.size()]));
        } else {
            if (list.size() <= 0) {
                return _mapType(cls);
            }
            return MapType.construct(cls, (JavaType) list.get(0), list.size() >= 2 ? (JavaType) list.get(1) : _unknownType());
        }
    }

    protected JavaType _fromVariable(TypeVariable<?> typeVariable, TypeBindings typeBindings) {
        if (typeBindings == null) {
            return _unknownType();
        }
        String name = typeVariable.getName();
        JavaType findType = typeBindings.findType(name);
        if (findType != null) {
            return findType;
        }
        Type[] bounds = typeVariable.getBounds();
        typeBindings._addPlaceholder(name);
        return _constructType(bounds[0], typeBindings);
    }

    protected JavaType _fromWildcard(WildcardType wildcardType, TypeBindings typeBindings) {
        return _constructType(wildcardType.getUpperBounds()[0], typeBindings);
    }

    protected JavaType _resolveVariableViaSubTypes(HierarchicType hierarchicType, String str, TypeBindings typeBindings) {
        if (hierarchicType != null && hierarchicType.isGeneric()) {
            TypeVariable[] typeParameters = hierarchicType.getRawClass().getTypeParameters();
            int i = 0;
            int length = typeParameters.length;
            while (i < length) {
                if (str.equals(typeParameters[i].getName())) {
                    Type type = hierarchicType.asGeneric().getActualTypeArguments()[i];
                    return type instanceof TypeVariable ? _resolveVariableViaSubTypes(hierarchicType.getSubType(), ((TypeVariable) type).getName(), typeBindings) : _constructType(type, typeBindings);
                } else {
                    i++;
                }
            }
        }
        return _unknownType();
    }

    protected JavaType _unknownType() {
        return new SimpleType(Object.class, null, null);
    }

    protected ArrayType constructArrayType(Class<?> cls) {
        return ArrayType.construct(_constructType(cls, null));
    }

    protected ArrayType constructArrayType(JavaType javaType) {
        return ArrayType.construct(javaType);
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, Class<?> cls2) {
        return CollectionLikeType.construct(cls, constructType((Type) cls2));
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, JavaType javaType) {
        return CollectionLikeType.construct(cls, javaType);
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, Class<?> cls2) {
        return CollectionType.construct(cls, constructType((Type) cls2));
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, JavaType javaType) {
        return CollectionType.construct(cls, javaType);
    }

    public JavaType constructFromCanonical(String str) {
        return this._parser.parse(str);
    }

    public MapLikeType constructMapLikeType(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        return MapType.construct(cls, constructType((Type) cls2), constructType((Type) cls3));
    }

    public MapLikeType constructMapLikeType(Class<?> cls, JavaType javaType, JavaType javaType2) {
        return MapLikeType.construct(cls, javaType, javaType2);
    }

    public MapType constructMapType(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        return MapType.construct(cls, constructType((Type) cls2), constructType((Type) cls3));
    }

    public MapType constructMapType(Class<? extends Map> cls, JavaType javaType, JavaType javaType2) {
        return MapType.construct(cls, javaType, javaType2);
    }

    public JavaType constructParametricType(Class<?> cls, Class<?>... clsArr) {
        int length = clsArr.length;
        JavaType[] javaTypeArr = new JavaType[length];
        for (int i = 0; i < length; i++) {
            javaTypeArr[i] = _fromClass(clsArr[i], null);
        }
        return constructParametricType((Class) cls, javaTypeArr);
    }

    public JavaType constructParametricType(Class<?> cls, JavaType... javaTypeArr) {
        if (cls.isArray()) {
            if (javaTypeArr.length == 1) {
                return constructArrayType(javaTypeArr[0]);
            }
            throw new IllegalArgumentException("Need exactly 1 parameter type for arrays (" + cls.getName() + ")");
        } else if (Map.class.isAssignableFrom(cls)) {
            if (javaTypeArr.length == 2) {
                return constructMapType((Class) cls, javaTypeArr[0], javaTypeArr[1]);
            }
            throw new IllegalArgumentException("Need exactly 2 parameter types for Map types (" + cls.getName() + ")");
        } else if (!Collection.class.isAssignableFrom(cls)) {
            return constructSimpleType(cls, javaTypeArr);
        } else {
            if (javaTypeArr.length == 1) {
                return constructCollectionType((Class) cls, javaTypeArr[0]);
            }
            throw new IllegalArgumentException("Need exactly 1 parameter type for Collection types (" + cls.getName() + ")");
        }
    }

    public JavaType constructSimpleType(Class<?> cls, JavaType[] javaTypeArr) {
        TypeVariable[] typeParameters = cls.getTypeParameters();
        if (typeParameters.length != javaTypeArr.length) {
            throw new IllegalArgumentException("Parameter type mismatch for " + cls.getName() + ": expected " + typeParameters.length + " parameters, was given " + javaTypeArr.length);
        }
        String[] strArr = new String[typeParameters.length];
        int length = typeParameters.length;
        for (int i = 0; i < length; i++) {
            strArr[i] = typeParameters[i].getName();
        }
        return new SimpleType(cls, strArr, javaTypeArr);
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        if (!(javaType instanceof SimpleType) || (!cls.isArray() && !Map.class.isAssignableFrom(cls) && !Collection.class.isAssignableFrom(cls))) {
            return javaType.narrowBy(cls);
        }
        if (javaType.getRawClass().isAssignableFrom(cls)) {
            JavaType _fromClass = instance._fromClass(cls, new TypeBindings(this, javaType.getRawClass()));
            Object valueHandler = javaType.getValueHandler();
            if (valueHandler != null) {
                _fromClass.setValueHandler(valueHandler);
            }
            valueHandler = javaType.getTypeHandler();
            return valueHandler != null ? _fromClass.withTypeHandler(valueHandler) : _fromClass;
        } else {
            throw new IllegalArgumentException("Class " + cls.getClass().getName() + " not subtype of " + javaType);
        }
    }

    public JavaType constructType(Type type) {
        return _constructType(type, null);
    }

    public JavaType constructType(Type type, Class<?> cls) {
        return _constructType(type, new TypeBindings(this, (Class) cls));
    }

    public JavaType constructType(Type type, TypeBindings typeBindings) {
        return _constructType(type, typeBindings);
    }

    public JavaType constructType(Type type, JavaType javaType) {
        return _constructType(type, new TypeBindings(this, javaType));
    }

    public JavaType constructType(TypeReference<?> typeReference) {
        return _constructType(typeReference.getType(), null);
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2) {
        return findTypeParameters(cls, cls2, new TypeBindings(this, (Class) cls));
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2, TypeBindings typeBindings) {
        HierarchicType _findSuperTypeChain = _findSuperTypeChain(cls, cls2);
        if (_findSuperTypeChain == null) {
            throw new IllegalArgumentException("Class " + cls.getName() + " is not a subtype of " + cls2.getName());
        }
        while (_findSuperTypeChain.getSuperType() != null) {
            _findSuperTypeChain = _findSuperTypeChain.getSuperType();
            Class rawClass = _findSuperTypeChain.getRawClass();
            TypeBindings typeBindings2 = new TypeBindings(this, rawClass);
            if (_findSuperTypeChain.isGeneric()) {
                Type[] actualTypeArguments = _findSuperTypeChain.asGeneric().getActualTypeArguments();
                TypeVariable[] typeParameters = rawClass.getTypeParameters();
                int length = actualTypeArguments.length;
                for (int i = 0; i < length; i++) {
                    typeBindings2.addBinding(typeParameters[i].getName(), instance._constructType(actualTypeArguments[i], typeBindings));
                }
            }
            typeBindings = typeBindings2;
        }
        return !_findSuperTypeChain.isGeneric() ? null : typeBindings.typesAsArray();
    }

    public JavaType[] findTypeParameters(JavaType javaType, Class<?> cls) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass != cls) {
            return findTypeParameters(rawClass, cls, new TypeBindings(this, javaType));
        }
        int containedTypeCount = javaType.containedTypeCount();
        if (containedTypeCount == 0) {
            return null;
        }
        JavaType[] javaTypeArr = new JavaType[containedTypeCount];
        for (int i = 0; i < containedTypeCount; i++) {
            javaTypeArr[i] = javaType.containedType(i);
        }
        return javaTypeArr;
    }

    public JavaType uncheckedSimpleType(Class<?> cls) {
        return new SimpleType(cls, null, null);
    }

    public TypeFactory withModifier(TypeModifier typeModifier) {
        if (this._modifiers != null) {
            return new TypeFactory(this._parser, (TypeModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, typeModifier));
        }
        return new TypeFactory(this._parser, new TypeModifier[]{typeModifier});
    }
}
