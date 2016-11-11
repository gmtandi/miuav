package org.codehaus.jackson.org.objectweb.asm;

public class MethodAdapter implements MethodVisitor {
    protected MethodVisitor mv;

    public MethodAdapter(MethodVisitor methodVisitor) {
        this.mv = methodVisitor;
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        return this.mv.visitAnnotation(str, z);
    }

    public AnnotationVisitor visitAnnotationDefault() {
        return this.mv.visitAnnotationDefault();
    }

    public void visitAttribute(Attribute attribute) {
        this.mv.visitAttribute(attribute);
    }

    public void visitCode() {
        this.mv.visitCode();
    }

    public void visitEnd() {
        this.mv.visitEnd();
    }

    public void visitFieldInsn(int i, String str, String str2, String str3) {
        this.mv.visitFieldInsn(i, str, str2, str3);
    }

    public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        this.mv.visitFrame(i, i2, objArr, i3, objArr2);
    }

    public void visitIincInsn(int i, int i2) {
        this.mv.visitIincInsn(i, i2);
    }

    public void visitInsn(int i) {
        this.mv.visitInsn(i);
    }

    public void visitIntInsn(int i, int i2) {
        this.mv.visitIntInsn(i, i2);
    }

    public void visitJumpInsn(int i, Label label) {
        this.mv.visitJumpInsn(i, label);
    }

    public void visitLabel(Label label) {
        this.mv.visitLabel(label);
    }

    public void visitLdcInsn(Object obj) {
        this.mv.visitLdcInsn(obj);
    }

    public void visitLineNumber(int i, Label label) {
        this.mv.visitLineNumber(i, label);
    }

    public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
        this.mv.visitLocalVariable(str, str2, str3, label, label2, i);
    }

    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        this.mv.visitLookupSwitchInsn(label, iArr, labelArr);
    }

    public void visitMaxs(int i, int i2) {
        this.mv.visitMaxs(i, i2);
    }

    public void visitMethodInsn(int i, String str, String str2, String str3) {
        this.mv.visitMethodInsn(i, str, str2, str3);
    }

    public void visitMultiANewArrayInsn(String str, int i) {
        this.mv.visitMultiANewArrayInsn(str, i);
    }

    public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
        return this.mv.visitParameterAnnotation(i, str, z);
    }

    public void visitTableSwitchInsn(int i, int i2, Label label, Label[] labelArr) {
        this.mv.visitTableSwitchInsn(i, i2, label, labelArr);
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        this.mv.visitTryCatchBlock(label, label2, label3, str);
    }

    public void visitTypeInsn(int i, String str) {
        this.mv.visitTypeInsn(i, str);
    }

    public void visitVarInsn(int i, int i2) {
        this.mv.visitVarInsn(i, i2);
    }
}
