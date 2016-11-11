package org.codehaus.jackson.map.type;

public final class ClassKey implements Comparable<ClassKey> {
    Class<?> _class;
    String _className;
    int _hashCode;

    public ClassKey() {
        this._class = null;
        this._className = null;
        this._hashCode = 0;
    }

    public ClassKey(Class<?> cls) {
        this._class = cls;
        this._className = cls.getName();
        this._hashCode = this._className.hashCode();
    }

    public int compareTo(ClassKey classKey) {
        return this._className.compareTo(classKey._className);
    }

    public boolean equals(Object obj) {
        return obj == this ? true : obj == null ? false : obj.getClass() != getClass() ? false : ((ClassKey) obj)._class == this._class;
    }

    public int hashCode() {
        return this._hashCode;
    }

    public void reset(Class<?> cls) {
        this._class = cls;
        this._className = cls.getName();
        this._hashCode = this._className.hashCode();
    }

    public String toString() {
        return this._className;
    }
}
