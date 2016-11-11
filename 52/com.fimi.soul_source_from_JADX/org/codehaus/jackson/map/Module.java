package org.codehaus.jackson.map;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import org.codehaus.jackson.map.ser.BeanSerializerModifier;
import org.codehaus.jackson.map.type.TypeModifier;

public abstract class Module implements Versioned {

    public interface SetupContext {
        void addAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver);

        void addBeanDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier);

        void addBeanSerializerModifier(BeanSerializerModifier beanSerializerModifier);

        void addDeserializers(Deserializers deserializers);

        void addKeyDeserializers(KeyDeserializers keyDeserializers);

        void addKeySerializers(Serializers serializers);

        void addSerializers(Serializers serializers);

        void addTypeModifier(TypeModifier typeModifier);

        void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

        DeserializationConfig getDeserializationConfig();

        Version getMapperVersion();

        SerializationConfig getSerializationConfig();

        void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

        void setMixInAnnotations(Class<?> cls, Class<?> cls2);
    }

    public abstract String getModuleName();

    public abstract void setupModule(SetupContext setupContext);

    public abstract Version version();
}
