package org.codehaus.jackson.org.objectweb.asm;

public interface MethodVisitor {
    AnnotationVisitor visitAnnotation(String str, boolean z);

    AnnotationVisitor visitAnnotationDefault();

    void visitAttribute(Attribute attribute);

    void visitCode();

    void visitEnd();

    void visitFieldInsn(int i, String str, String str2, String str3);

    void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2);

    void visitIincInsn(int i, int i2);

    void visitInsn(int i);

    void visitIntInsn(int i, int i2);

    void visitJumpInsn(int i, Label label);

    void visitLabel(Label label);

    void visitLdcInsn(Object obj);

    void visitLineNumber(int i, Label label);

    void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i);

    void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr);

    void visitMaxs(int i, int i2);

    void visitMethodInsn(int i, String str, String str2, String str3);

    void visitMultiANewArrayInsn(String str, int i);

    AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z);

    void visitTableSwitchInsn(int i, int i2, Label label, Label[] labelArr);

    void visitTryCatchBlock(Label label, Label label2, Label label3, String str);

    void visitTypeInsn(int i, String str);

    void visitVarInsn(int i, int i2);
}
