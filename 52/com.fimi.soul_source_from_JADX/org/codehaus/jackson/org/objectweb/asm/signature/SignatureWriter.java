package org.codehaus.jackson.org.objectweb.asm.signature;

public class SignatureWriter implements SignatureVisitor {
    private final StringBuffer f16272a;
    private boolean f16273b;
    private boolean f16274c;
    private int f16275d;

    public SignatureWriter() {
        this.f16272a = new StringBuffer();
    }

    private void m19388a() {
        if (this.f16273b) {
            this.f16273b = false;
            this.f16272a.append('>');
        }
    }

    private void m19389b() {
        if (this.f16275d % 2 != 0) {
            this.f16272a.append('>');
        }
        this.f16275d /= 2;
    }

    public String toString() {
        return this.f16272a.toString();
    }

    public SignatureVisitor visitArrayType() {
        this.f16272a.append('[');
        return this;
    }

    public void visitBaseType(char c) {
        this.f16272a.append(c);
    }

    public SignatureVisitor visitClassBound() {
        return this;
    }

    public void visitClassType(String str) {
        this.f16272a.append('L');
        this.f16272a.append(str);
        this.f16275d *= 2;
    }

    public void visitEnd() {
        m19389b();
        this.f16272a.append(';');
    }

    public SignatureVisitor visitExceptionType() {
        this.f16272a.append('^');
        return this;
    }

    public void visitFormalTypeParameter(String str) {
        if (!this.f16273b) {
            this.f16273b = true;
            this.f16272a.append('<');
        }
        this.f16272a.append(str);
        this.f16272a.append(':');
    }

    public void visitInnerClassType(String str) {
        m19389b();
        this.f16272a.append('.');
        this.f16272a.append(str);
        this.f16275d *= 2;
    }

    public SignatureVisitor visitInterface() {
        return this;
    }

    public SignatureVisitor visitInterfaceBound() {
        this.f16272a.append(':');
        return this;
    }

    public SignatureVisitor visitParameterType() {
        m19388a();
        if (!this.f16274c) {
            this.f16274c = true;
            this.f16272a.append('(');
        }
        return this;
    }

    public SignatureVisitor visitReturnType() {
        m19388a();
        if (!this.f16274c) {
            this.f16272a.append('(');
        }
        this.f16272a.append(')');
        return this;
    }

    public SignatureVisitor visitSuperclass() {
        m19388a();
        return this;
    }

    public SignatureVisitor visitTypeArgument(char c) {
        if (this.f16275d % 2 == 0) {
            this.f16275d++;
            this.f16272a.append('<');
        }
        if (c != SignatureVisitor.INSTANCEOF) {
            this.f16272a.append(c);
        }
        return this;
    }

    public void visitTypeArgument() {
        if (this.f16275d % 2 == 0) {
            this.f16275d++;
            this.f16272a.append('<');
        }
        this.f16272a.append('*');
    }

    public void visitTypeVariable(String str) {
        this.f16272a.append('T');
        this.f16272a.append(str);
        this.f16272a.append(';');
    }
}
