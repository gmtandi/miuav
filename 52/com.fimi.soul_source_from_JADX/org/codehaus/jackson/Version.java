package org.codehaus.jackson;

import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;

public class Version implements Comparable<Version> {
    private static final Version UNKNOWN_VERSION;
    protected final int _majorVersion;
    protected final int _minorVersion;
    protected final int _patchLevel;
    protected final String _snapshotInfo;

    static {
        UNKNOWN_VERSION = new Version(0, 0, 0, null);
    }

    public Version(int i, int i2, int i3, String str) {
        this._majorVersion = i;
        this._minorVersion = i2;
        this._patchLevel = i3;
        this._snapshotInfo = str;
    }

    public static Version unknownVersion() {
        return UNKNOWN_VERSION;
    }

    public int compareTo(Version version) {
        int i = this._majorVersion - version._majorVersion;
        if (i != 0) {
            return i;
        }
        i = this._minorVersion - version._minorVersion;
        return i == 0 ? this._patchLevel - version._patchLevel : i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Version version = (Version) obj;
        return version._majorVersion == this._majorVersion && version._minorVersion == this._minorVersion && version._patchLevel == this._patchLevel;
    }

    public int getMajorVersion() {
        return this._majorVersion;
    }

    public int getMinorVersion() {
        return this._minorVersion;
    }

    public int getPatchLevel() {
        return this._patchLevel;
    }

    public int hashCode() {
        return (this._majorVersion + this._minorVersion) + this._patchLevel;
    }

    public boolean isSnapshot() {
        return this._snapshotInfo != null && this._snapshotInfo.length() > 0;
    }

    public boolean isUknownVersion() {
        return this == UNKNOWN_VERSION;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this._majorVersion).append('.');
        stringBuilder.append(this._minorVersion).append('.');
        stringBuilder.append(this._patchLevel);
        if (isSnapshot()) {
            stringBuilder.append(SignatureVisitor.SUPER).append(this._snapshotInfo);
        }
        return stringBuilder.toString();
    }
}
