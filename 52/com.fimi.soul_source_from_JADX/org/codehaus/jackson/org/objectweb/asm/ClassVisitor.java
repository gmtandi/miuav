package org.codehaus.jackson.org.objectweb.asm;

public interface ClassVisitor {
    void visit(int i, int i2, String str, String str2, String str3, String[] strArr);

    AnnotationVisitor visitAnnotation(String str, boolean z);

    void visitAttribute(Attribute attribute);

    void visitEnd();

    FieldVisitor visitField(int i, String str, String str2, String str3, Object obj);

    void visitInnerClass(String str, String str2, String str3, int i);

    MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr);

    void visitOuterClass(String str, String str2, String str3);

    void visitSource(String str, String str2);
}
