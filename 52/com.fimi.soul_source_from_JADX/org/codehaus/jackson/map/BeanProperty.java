package org.codehaus.jackson.map;

import java.lang.annotation.Annotation;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

public interface BeanProperty {

    public class Std implements BeanProperty {
        protected final Annotations _contextAnnotations;
        protected final AnnotatedMember _member;
        protected final String _name;
        protected final JavaType _type;

        public Std(String str, JavaType javaType, Annotations annotations, AnnotatedMember annotatedMember) {
            this._name = str;
            this._type = javaType;
            this._member = annotatedMember;
            this._contextAnnotations = annotations;
        }

        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return this._member.getAnnotation(cls);
        }

        public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
            return this._contextAnnotations == null ? null : this._contextAnnotations.get(cls);
        }

        public AnnotatedMember getMember() {
            return this._member;
        }

        public String getName() {
            return this._name;
        }

        public JavaType getType() {
            return this._type;
        }

        public Std withType(JavaType javaType) {
            return new Std(this._name, javaType, this._contextAnnotations, this._member);
        }
    }

    <A extends Annotation> A getAnnotation(Class<A> cls);

    <A extends Annotation> A getContextAnnotation(Class<A> cls);

    AnnotatedMember getMember();

    String getName();

    JavaType getType();
}
