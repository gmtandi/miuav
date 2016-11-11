package org.codehaus.jackson.org.objectweb.asm.signature;

public interface SignatureVisitor {
    public static final char EXTENDS = '+';
    public static final char INSTANCEOF = '=';
    public static final char SUPER = '-';

    SignatureVisitor visitArrayType();

    void visitBaseType(char c);

    SignatureVisitor visitClassBound();

    void visitClassType(String str);

    void visitEnd();

    SignatureVisitor visitExceptionType();

    void visitFormalTypeParameter(String str);

    void visitInnerClassType(String str);

    SignatureVisitor visitInterface();

    SignatureVisitor visitInterfaceBound();

    SignatureVisitor visitParameterType();

    SignatureVisitor visitReturnType();

    SignatureVisitor visitSuperclass();

    SignatureVisitor visitTypeArgument(char c);

    void visitTypeArgument();

    void visitTypeVariable(String str);
}
