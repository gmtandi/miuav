package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JacksonAnnotation;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonClass;
import org.codehaus.jackson.annotate.JsonContentClass;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonGetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.codehaus.jackson.annotate.JsonKeyClass;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonRawValue;
import org.codehaus.jackson.annotate.JsonSetter;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonDeserializer.None;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.annotate.JsonCachable;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.annotate.JsonSerialize.Typing;
import org.codehaus.jackson.map.annotate.JsonTypeIdResolver;
import org.codehaus.jackson.map.annotate.JsonTypeResolver;
import org.codehaus.jackson.map.annotate.JsonView;
import org.codehaus.jackson.map.annotate.NoClass;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import org.codehaus.jackson.map.ser.impl.RawSerializer;
import org.codehaus.jackson.type.JavaType;
import org.p122a.p123a.C2915a;

public class JacksonAnnotationIntrospector extends AnnotationIntrospector {
    protected StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
        return new StdTypeResolverBuilder();
    }

    protected TypeResolverBuilder<?> _findTypeResolver(MapperConfig<?> mapperConfig, Annotated annotated, JavaType javaType) {
        TypeResolverBuilder typeResolverBuilderInstance;
        TypeIdResolver typeIdResolver = null;
        JsonTypeInfo jsonTypeInfo = (JsonTypeInfo) annotated.getAnnotation(JsonTypeInfo.class);
        JsonTypeResolver jsonTypeResolver = (JsonTypeResolver) annotated.getAnnotation(JsonTypeResolver.class);
        if (jsonTypeResolver != null) {
            if (jsonTypeInfo == null) {
                return null;
            }
            typeResolverBuilderInstance = mapperConfig.typeResolverBuilderInstance(annotated, jsonTypeResolver.value());
        } else if (jsonTypeInfo == null || jsonTypeInfo.use() == Id.NONE) {
            return null;
        } else {
            Object _constructStdTypeResolverBuilder = _constructStdTypeResolverBuilder();
        }
        JsonTypeIdResolver jsonTypeIdResolver = (JsonTypeIdResolver) annotated.getAnnotation(JsonTypeIdResolver.class);
        if (jsonTypeIdResolver != null) {
            typeIdResolver = mapperConfig.typeIdResolverInstance(annotated, jsonTypeIdResolver.value());
        }
        if (typeIdResolver != null) {
            typeIdResolver.init(javaType);
        }
        return typeResolverBuilderInstance.init(jsonTypeInfo.use(), typeIdResolver).inclusion(jsonTypeInfo.include()).typeProperty(jsonTypeInfo.property());
    }

    protected boolean _isIgnorable(Annotated annotated) {
        JsonIgnore jsonIgnore = (JsonIgnore) annotated.getAnnotation(JsonIgnore.class);
        return jsonIgnore != null && jsonIgnore.value();
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        JsonAutoDetect jsonAutoDetect = (JsonAutoDetect) annotatedClass.getAnnotation(JsonAutoDetect.class);
        return jsonAutoDetect == null ? visibilityChecker : visibilityChecker.with(jsonAutoDetect);
    }

    public Boolean findCachability(AnnotatedClass annotatedClass) {
        JsonCachable jsonCachable = (JsonCachable) annotatedClass.getAnnotation(JsonCachable.class);
        return jsonCachable == null ? null : jsonCachable.value() ? Boolean.TRUE : Boolean.FALSE;
    }

    public Class<? extends JsonDeserializer<?>> findContentDeserializer(Annotated annotated) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize != null) {
            Class<? extends JsonDeserializer<?>> contentUsing = jsonDeserialize.contentUsing();
            if (contentUsing != None.class) {
                return contentUsing;
            }
        }
        return null;
    }

    public Class<? extends JsonSerializer<?>> findContentSerializer(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            Class<? extends JsonSerializer<?>> contentUsing = jsonSerialize.contentUsing();
            if (contentUsing != JsonSerializer.None.class) {
                return contentUsing;
            }
        }
        return null;
    }

    public String findDeserializablePropertyName(AnnotatedField annotatedField) {
        JsonProperty jsonProperty = (JsonProperty) annotatedField.getAnnotation(JsonProperty.class);
        return jsonProperty != null ? jsonProperty.value() : (annotatedField.hasAnnotation(JsonDeserialize.class) || annotatedField.hasAnnotation(JsonView.class)) ? C2915a.f14760f : null;
    }

    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType, String str) {
        Class<?> contentAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize != null) {
            contentAs = jsonDeserialize.contentAs();
            if (contentAs != NoClass.class) {
                return contentAs;
            }
        }
        JsonContentClass jsonContentClass = (JsonContentClass) annotated.getAnnotation(JsonContentClass.class);
        if (jsonContentClass != null) {
            contentAs = jsonContentClass.value();
            if (contentAs != NoClass.class) {
                return contentAs;
            }
        }
        return null;
    }

    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType, String str) {
        Class<?> keyAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize != null) {
            keyAs = jsonDeserialize.keyAs();
            if (keyAs != NoClass.class) {
                return keyAs;
            }
        }
        JsonKeyClass jsonKeyClass = (JsonKeyClass) annotated.getAnnotation(JsonKeyClass.class);
        if (jsonKeyClass != null) {
            keyAs = jsonKeyClass.value();
            if (keyAs != NoClass.class) {
                return keyAs;
            }
        }
        return null;
    }

    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType, String str) {
        Class<?> as;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize != null) {
            as = jsonDeserialize.as();
            if (as != NoClass.class) {
                return as;
            }
        }
        JsonClass jsonClass = (JsonClass) annotated.getAnnotation(JsonClass.class);
        if (jsonClass != null) {
            as = jsonClass.value();
            if (as != NoClass.class) {
                return as;
            }
        }
        return null;
    }

    public Class<? extends JsonDeserializer<?>> findDeserializer(Annotated annotated, BeanProperty beanProperty) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize != null) {
            Class<? extends JsonDeserializer<?>> using = jsonDeserialize.using();
            if (using != None.class) {
                return using;
            }
        }
        return null;
    }

    public String findEnumValue(Enum<?> enumR) {
        return enumR.name();
    }

    public Object findFilterId(AnnotatedClass annotatedClass) {
        JsonFilter jsonFilter = (JsonFilter) annotatedClass.getAnnotation(JsonFilter.class);
        if (jsonFilter != null) {
            String value = jsonFilter.value();
            if (value.length() > 0) {
                return value;
            }
        }
        return null;
    }

    public String findGettablePropertyName(AnnotatedMethod annotatedMethod) {
        JsonProperty jsonProperty = (JsonProperty) annotatedMethod.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        JsonGetter jsonGetter = (JsonGetter) annotatedMethod.getAnnotation(JsonGetter.class);
        return jsonGetter != null ? jsonGetter.value() : (annotatedMethod.hasAnnotation(JsonSerialize.class) || annotatedMethod.hasAnnotation(JsonView.class)) ? C2915a.f14760f : null;
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) annotatedClass.getAnnotation(JsonIgnoreProperties.class);
        return jsonIgnoreProperties == null ? null : Boolean.valueOf(jsonIgnoreProperties.ignoreUnknown());
    }

    public Class<? extends KeyDeserializer> findKeyDeserializer(Annotated annotated) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize != null) {
            Class<? extends KeyDeserializer> keyUsing = jsonDeserialize.keyUsing();
            if (keyUsing != KeyDeserializer.None.class) {
                return keyUsing;
            }
        }
        return null;
    }

    public Class<? extends JsonSerializer<?>> findKeySerializer(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            Class<? extends JsonSerializer<?>> keyUsing = jsonSerialize.keyUsing();
            if (keyUsing != JsonSerializer.None.class) {
                return keyUsing;
            }
        }
        return null;
    }

    public String[] findPropertiesToIgnore(AnnotatedClass annotatedClass) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) annotatedClass.getAnnotation(JsonIgnoreProperties.class);
        return jsonIgnoreProperties == null ? null : jsonIgnoreProperties.value();
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        if (javaType.isContainerType()) {
            return _findTypeResolver(mapperConfig, annotatedMember, javaType);
        }
        throw new IllegalArgumentException("Must call method with a container type (got " + javaType + ")");
    }

    public String findPropertyNameForParam(AnnotatedParameter annotatedParameter) {
        if (annotatedParameter != null) {
            JsonProperty jsonProperty = (JsonProperty) annotatedParameter.getAnnotation(JsonProperty.class);
            if (jsonProperty != null) {
                return jsonProperty.value();
            }
        }
        return null;
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        return javaType.isContainerType() ? null : _findTypeResolver(mapperConfig, annotatedMember, javaType);
    }

    public ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        JsonManagedReference jsonManagedReference = (JsonManagedReference) annotatedMember.getAnnotation(JsonManagedReference.class);
        if (jsonManagedReference != null) {
            return ReferenceProperty.managed(jsonManagedReference.value());
        }
        JsonBackReference jsonBackReference = (JsonBackReference) annotatedMember.getAnnotation(JsonBackReference.class);
        return jsonBackReference != null ? ReferenceProperty.back(jsonBackReference.value()) : null;
    }

    public String findRootName(AnnotatedClass annotatedClass) {
        return null;
    }

    public String findSerializablePropertyName(AnnotatedField annotatedField) {
        JsonProperty jsonProperty = (JsonProperty) annotatedField.getAnnotation(JsonProperty.class);
        return jsonProperty != null ? jsonProperty.value() : (annotatedField.hasAnnotation(JsonSerialize.class) || annotatedField.hasAnnotation(JsonView.class)) ? C2915a.f14760f : null;
    }

    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            Class<?> contentAs = jsonSerialize.contentAs();
            if (contentAs != NoClass.class) {
                return contentAs;
            }
        }
        return null;
    }

    public Inclusion findSerializationInclusion(Annotated annotated, Inclusion inclusion) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            return jsonSerialize.include();
        }
        JsonWriteNullProperties jsonWriteNullProperties = (JsonWriteNullProperties) annotated.getAnnotation(JsonWriteNullProperties.class);
        return jsonWriteNullProperties != null ? jsonWriteNullProperties.value() ? Inclusion.ALWAYS : Inclusion.NON_NULL : inclusion;
    }

    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            Class<?> keyAs = jsonSerialize.keyAs();
            if (keyAs != NoClass.class) {
                return keyAs;
            }
        }
        return null;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) annotatedClass.getAnnotation(JsonPropertyOrder.class);
        return jsonPropertyOrder == null ? null : jsonPropertyOrder.value();
    }

    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) annotatedClass.getAnnotation(JsonPropertyOrder.class);
        return jsonPropertyOrder == null ? null : Boolean.valueOf(jsonPropertyOrder.alphabetic());
    }

    public Class<?> findSerializationType(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            Class<?> as = jsonSerialize.as();
            if (as != NoClass.class) {
                return as;
            }
        }
        return null;
    }

    public Typing findSerializationTyping(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        return jsonSerialize == null ? null : jsonSerialize.typing();
    }

    public Class<?>[] findSerializationViews(Annotated annotated) {
        JsonView jsonView = (JsonView) annotated.getAnnotation(JsonView.class);
        return jsonView == null ? null : jsonView.value();
    }

    public Object findSerializer(Annotated annotated, BeanProperty beanProperty) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            Class using = jsonSerialize.using();
            if (using != JsonSerializer.None.class) {
                return using;
            }
        }
        JsonRawValue jsonRawValue = (JsonRawValue) annotated.getAnnotation(JsonRawValue.class);
        return (jsonRawValue == null || !jsonRawValue.value()) ? null : new RawSerializer(annotated.getRawType());
    }

    public String findSettablePropertyName(AnnotatedMethod annotatedMethod) {
        JsonProperty jsonProperty = (JsonProperty) annotatedMethod.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        JsonSetter jsonSetter = (JsonSetter) annotatedMethod.getAnnotation(JsonSetter.class);
        return jsonSetter != null ? jsonSetter.value() : (annotatedMethod.hasAnnotation(JsonDeserialize.class) || annotatedMethod.hasAnnotation(JsonView.class)) ? C2915a.f14760f : null;
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        JsonSubTypes jsonSubTypes = (JsonSubTypes) annotated.getAnnotation(JsonSubTypes.class);
        if (jsonSubTypes == null) {
            return null;
        }
        Type[] value = jsonSubTypes.value();
        List arrayList = new ArrayList(value.length);
        for (Type type : value) {
            arrayList.add(new NamedType(type.value(), type.name()));
        }
        return arrayList;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        JsonTypeName jsonTypeName = (JsonTypeName) annotatedClass.getAnnotation(JsonTypeName.class);
        return jsonTypeName == null ? null : jsonTypeName.value();
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        return _findTypeResolver(mapperConfig, annotatedClass, javaType);
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.hasAnnotation(JsonAnyGetter.class);
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.hasAnnotation(JsonAnySetter.class);
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        JsonValue jsonValue = (JsonValue) annotatedMethod.getAnnotation(JsonValue.class);
        return jsonValue != null && jsonValue.value();
    }

    public boolean hasCreatorAnnotation(Annotated annotated) {
        return annotated.hasAnnotation(JsonCreator.class);
    }

    public boolean isHandled(Annotation annotation) {
        return annotation.annotationType().getAnnotation(JacksonAnnotation.class) != null;
    }

    public boolean isIgnorableConstructor(AnnotatedConstructor annotatedConstructor) {
        return _isIgnorable(annotatedConstructor);
    }

    public boolean isIgnorableField(AnnotatedField annotatedField) {
        return _isIgnorable(annotatedField);
    }

    public boolean isIgnorableMethod(AnnotatedMethod annotatedMethod) {
        return _isIgnorable(annotatedMethod);
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        JsonIgnoreType jsonIgnoreType = (JsonIgnoreType) annotatedClass.getAnnotation(JsonIgnoreType.class);
        return jsonIgnoreType == null ? null : Boolean.valueOf(jsonIgnoreType.value());
    }
}
