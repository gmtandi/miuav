package org.codehaus.jackson.org.objectweb.asm;

public interface AnnotationVisitor {
    void visit(String str, Object obj);

    AnnotationVisitor visitAnnotation(String str, String str2);

    AnnotationVisitor visitArray(String str);

    void visitEnd();

    void visitEnum(String str, String str2, String str3);
}
