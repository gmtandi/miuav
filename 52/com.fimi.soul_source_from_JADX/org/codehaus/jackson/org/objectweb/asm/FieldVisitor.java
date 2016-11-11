package org.codehaus.jackson.org.objectweb.asm;

public interface FieldVisitor {
    AnnotationVisitor visitAnnotation(String str, boolean z);

    void visitAttribute(Attribute attribute);

    void visitEnd();
}
