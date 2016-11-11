package com.facebook.common.util;

import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public enum TriState {
    YES,
    NO,
    UNSET;

    /* renamed from: com.facebook.common.util.TriState.1 */
    /* synthetic */ class C09751 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$common$util$TriState;

        static {
            $SwitchMap$com$facebook$common$util$TriState = new int[TriState.values().length];
            try {
                $SwitchMap$com$facebook$common$util$TriState[TriState.YES.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$facebook$common$util$TriState[TriState.NO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$facebook$common$util$TriState[TriState.UNSET.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static TriState fromDbValue(int i) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return YES;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return NO;
            default:
                return UNSET;
        }
    }

    public static TriState valueOf(Boolean bool) {
        return bool != null ? valueOf(bool.booleanValue()) : UNSET;
    }

    public static TriState valueOf(boolean z) {
        return z ? YES : NO;
    }

    public boolean asBoolean() {
        switch (C09751.$SwitchMap$com$facebook$common$util$TriState[ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return true;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return false;
            case Type.BYTE /*3*/:
                throw new IllegalStateException("No boolean equivalent for UNSET");
            default:
                throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
    }

    public boolean asBoolean(boolean z) {
        switch (C09751.$SwitchMap$com$facebook$common$util$TriState[ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return true;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return false;
            case Type.BYTE /*3*/:
                return z;
            default:
                throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
    }

    public Boolean asBooleanObject() {
        switch (C09751.$SwitchMap$com$facebook$common$util$TriState[ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return Boolean.TRUE;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return Boolean.FALSE;
            case Type.BYTE /*3*/:
                return null;
            default:
                throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
    }

    public int getDbValue() {
        switch (C09751.$SwitchMap$com$facebook$common$util$TriState[ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return 1;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return 2;
            default:
                return 3;
        }
    }

    public boolean isSet() {
        return this != UNSET;
    }
}
