package org.codehaus.jackson.org.objectweb.asm;

public class ClassAdapter implements ClassVisitor {
    protected ClassVisitor cv;

    public ClassAdapter(ClassVisitor classVisitor) {
        this.cv = classVisitor;
    }

    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        this.cv.visit(i, i2, str, str2, str3, strArr);
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        return this.cv.visitAnnotation(str, z);
    }

    public void visitAttribute(Attribute attribute) {
        this.cv.visitAttribute(attribute);
    }

    public void visitEnd() {
        this.cv.visitEnd();
    }

    public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        return this.cv.visitField(i, str, str2, str3, obj);
    }

    public void visitInnerClass(String str, String str2, String str3, int i) {
        this.cv.visitInnerClass(str, str2, str3, i);
    }

    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        return this.cv.visitMethod(i, str, str2, str3, strArr);
    }

    public void visitOuterClass(String str, String str2, String str3) {
        this.cv.visitOuterClass(str, str2, str3);
    }

    public void visitSource(String str, String str2) {
        this.cv.visitSource(str, str2);
    }
}
