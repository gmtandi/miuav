package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;
import com.fimi.soul.view.photodraweeview.C2020f;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class ScalingUtils {

    /* renamed from: com.facebook.drawee.drawable.ScalingUtils.1 */
    /* synthetic */ class C09871 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$drawee$drawable$ScalingUtils$ScaleType;

        static {
            $SwitchMap$com$facebook$drawee$drawable$ScalingUtils$ScaleType = new int[ScaleType.values().length];
            try {
                $SwitchMap$com$facebook$drawee$drawable$ScalingUtils$ScaleType[ScaleType.FIT_XY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$facebook$drawee$drawable$ScalingUtils$ScaleType[ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$facebook$drawee$drawable$ScalingUtils$ScaleType[ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$facebook$drawee$drawable$ScalingUtils$ScaleType[ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$facebook$drawee$drawable$ScalingUtils$ScaleType[ScaleType.CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$facebook$drawee$drawable$ScalingUtils$ScaleType[ScaleType.CENTER_INSIDE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$facebook$drawee$drawable$ScalingUtils$ScaleType[ScaleType.CENTER_CROP.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$facebook$drawee$drawable$ScalingUtils$ScaleType[ScaleType.FOCUS_CROP.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public enum ScaleType {
        FIT_XY,
        FIT_START,
        FIT_CENTER,
        FIT_END,
        CENTER,
        CENTER_INSIDE,
        CENTER_CROP,
        FOCUS_CROP
    }

    public static Matrix getTransform(Matrix matrix, Rect rect, int i, int i2, float f, float f2, ScaleType scaleType) {
        int width = rect.width();
        int height = rect.height();
        float f3 = ((float) width) / ((float) i);
        float f4 = ((float) height) / ((float) i2);
        float f5;
        float f6;
        switch (C09871.$SwitchMap$com$facebook$drawee$drawable$ScalingUtils$ScaleType[scaleType.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                f5 = (float) rect.left;
                f6 = (float) rect.top;
                matrix.setScale(f3, f4);
                matrix.postTranslate((float) ((int) (f5 + 0.5f)), (float) ((int) (f6 + 0.5f)));
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                f5 = Math.min(f3, f4);
                f6 = (float) rect.left;
                f4 = (float) rect.top;
                matrix.setScale(f5, f5);
                matrix.postTranslate((float) ((int) (f6 + 0.5f)), (float) ((int) (f4 + 0.5f)));
                break;
            case Type.BYTE /*3*/:
                f6 = Math.min(f3, f4);
                f5 = ((((float) width) - (((float) i) * f6)) * 0.5f) + ((float) rect.left);
                f4 = ((float) rect.top) + ((((float) height) - (((float) i2) * f6)) * 0.5f);
                matrix.setScale(f6, f6);
                matrix.postTranslate((float) ((int) (f5 + 0.5f)), (float) ((int) (f4 + 0.5f)));
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                f6 = Math.min(f3, f4);
                f5 = (((float) width) - (((float) i) * f6)) + ((float) rect.left);
                f4 = ((float) rect.top) + (((float) height) - (((float) i2) * f6));
                matrix.setScale(f6, f6);
                matrix.postTranslate((float) ((int) (f5 + 0.5f)), (float) ((int) (f4 + 0.5f)));
                break;
            case Type.INT /*5*/:
                matrix.setTranslate((float) ((int) (((((float) (width - i)) * 0.5f) + ((float) rect.left)) + 0.5f)), (float) ((int) ((((float) rect.top) + (((float) (height - i2)) * 0.5f)) + 0.5f)));
                break;
            case Type.FLOAT /*6*/:
                f6 = Math.min(Math.min(f3, f4), C2020f.f10933c);
                f5 = ((((float) width) - (((float) i) * f6)) * 0.5f) + ((float) rect.left);
                f4 = ((float) rect.top) + ((((float) height) - (((float) i2) * f6)) * 0.5f);
                matrix.setScale(f6, f6);
                matrix.postTranslate((float) ((int) (f5 + 0.5f)), (float) ((int) (f4 + 0.5f)));
                break;
            case Type.LONG /*7*/:
                if (f4 > f3) {
                    f6 = ((float) rect.left) + ((((float) width) - (((float) i) * f4)) * 0.5f);
                    f5 = (float) rect.top;
                } else {
                    f6 = (float) rect.left;
                    f5 = ((float) rect.top) + ((((float) height) - (((float) i2) * f3)) * 0.5f);
                    f4 = f3;
                }
                matrix.setScale(f4, f4);
                matrix.postTranslate((float) ((int) (f6 + 0.5f)), (float) ((int) (f5 + 0.5f)));
                break;
            case Type.DOUBLE /*8*/:
                if (f4 > f3) {
                    f6 = ((float) rect.left) + Math.max(Math.min((((float) width) * 0.5f) - ((((float) i) * f4) * f), 0.0f), ((float) width) - (((float) i) * f4));
                    f5 = (float) rect.top;
                } else {
                    f6 = (float) rect.left;
                    f4 = (float) rect.top;
                    f5 = Math.max(Math.min((((float) height) * 0.5f) - ((((float) i2) * f3) * f2), 0.0f), ((float) height) - (((float) i2) * f3)) + f4;
                    f4 = f3;
                }
                matrix.setScale(f4, f4);
                matrix.postTranslate((float) ((int) (f6 + 0.5f)), (float) ((int) (f5 + 0.5f)));
                break;
            default:
                throw new UnsupportedOperationException("Unsupported scale type: " + scaleType);
        }
        return matrix;
    }
}
