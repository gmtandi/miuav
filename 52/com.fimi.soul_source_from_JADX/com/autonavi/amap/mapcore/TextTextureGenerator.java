package com.autonavi.amap.mapcore;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Typeface;
import android.os.Environment;
import android.text.TextPaint;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class TextTextureGenerator {
    private static final int ALIGNCENTER = 51;
    private static final int ALIGNLEFT = 49;
    private static final int ALIGNRIGHT = 50;
    static final int AN_LABEL_MAXCHARINLINE = 7;
    static final int AN_LABEL_MULITYLINE_SPAN = 2;
    static final int TEXT_FONTSIZE = 32;
    static final int TEXT_FONTSIZE_TRUE = 30;
    private float base_line;
    private float start_x;
    private Paint text_paint;

    public TextTextureGenerator() {
        this.base_line = 0.0f;
        this.start_x = 0.0f;
        this.text_paint = null;
        this.text_paint = newPaint(null, TEXT_FONTSIZE_TRUE, ALIGNLEFT);
        this.start_x = 0.0f;
        FontMetrics fontMetrics = this.text_paint.getFontMetrics();
        this.base_line = ((BitmapDescriptorFactory.HUE_ORANGE - (fontMetrics.bottom - fontMetrics.top)) / 2.0f) - fontMetrics.top;
    }

    public static int GetNearstSize2N(int i) {
        int i2 = 1;
        while (i > i2) {
            i2 *= AN_LABEL_MULITYLINE_SPAN;
        }
        return i2;
    }

    public static void generaAsccIITexturePng() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            OutputStream fileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "asccii.png"));
            Paint newPaint = newPaint(null, TEXT_FONTSIZE, ALIGNLEFT);
            Bitmap createBitmap = Bitmap.createBitmap(Opcodes.ACC_INTERFACE, Opcodes.ACC_INTERFACE, Config.ARGB_8888);
            FontMetricsInt fontMetricsInt = newPaint.getFontMetricsInt();
            Canvas canvas = new Canvas(createBitmap);
            Paint newPaint2 = newPaint(null, TEXT_FONTSIZE, ALIGNLEFT);
            float[] fArr = new float[1];
            for (int i = 0; i < 16; i++) {
                for (int i2 = 0; i2 < 16; i2++) {
                    char c = (char) ((i * 16) + i2);
                    canvas.drawText(c + C2915a.f14760f, (float) (i2 * 16), (float) (((i * 16) - fontMetricsInt.ascent) - 2), newPaint2);
                    newPaint2.getTextWidths(c + C2915a.f14760f, fArr);
                }
            }
            createBitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
            createBitmap.recycle();
        }
    }

    public static float getFontHeight(Paint paint) {
        FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.descent - fontMetrics.ascent;
    }

    public static float getFontlength(Paint paint, String str) {
        return paint.measureText(str);
    }

    private static Paint newPaint(String str, int i, int i2) {
        Paint textPaint = new TextPaint();
        textPaint.setColor(-1);
        textPaint.setTextSize((float) i);
        textPaint.setAntiAlias(true);
        textPaint.setFilterBitmap(true);
        textPaint.setTypeface(Typeface.DEFAULT);
        switch (i2) {
            case ALIGNLEFT /*49*/:
                textPaint.setTextAlign(Align.LEFT);
                break;
            case ALIGNRIGHT /*50*/:
                textPaint.setTextAlign(Align.RIGHT);
                break;
            case ALIGNCENTER /*51*/:
                textPaint.setTextAlign(Align.CENTER);
                break;
            default:
                textPaint.setTextAlign(Align.LEFT);
                break;
        }
        return textPaint;
    }

    public byte[] getCharsWidths(int[] iArr) {
        int length = iArr.length;
        byte[] bArr = new byte[length];
        float[] fArr = new float[1];
        for (int i = 0; i < length; i++) {
            fArr[0] = this.text_paint.measureText(((char) iArr[i]) + C2915a.f14760f);
            bArr[i] = (byte) ((int) (fArr[0] + C2020f.f10933c));
        }
        return bArr;
    }

    public String getFontVersion() {
        return Md5Utility.getByteArrayMD5(getTextPixelBuffer(39640));
    }

    public byte[] getTextPixelBuffer(int i) {
        try {
            char[] cArr = new char[]{(char) i};
            Bitmap createBitmap = Bitmap.createBitmap(TEXT_FONTSIZE, TEXT_FONTSIZE, Config.ALPHA_8);
            Canvas canvas = new Canvas(createBitmap);
            byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
            Buffer wrap = ByteBuffer.wrap(bArr);
            float measureText = this.text_paint.measureText(cArr[0] + C2915a.f14760f);
            Align textAlign = this.text_paint.getTextAlign();
            float f = measureText - BitmapDescriptorFactory.HUE_ORANGE;
            if (textAlign == Align.CENTER || f < 4.0f) {
                canvas.drawText(cArr, 0, 1, this.start_x, this.base_line - 2.0f, this.text_paint);
            } else {
                this.text_paint.setTextAlign(Align.CENTER);
                this.text_paint.setTextSize(BitmapDescriptorFactory.HUE_ORANGE - f);
                canvas.drawText(cArr, 0, 1, (BitmapDescriptorFactory.HUE_ORANGE - f) / 2.0f, this.base_line, this.text_paint);
                this.text_paint.setTextAlign(textAlign);
            }
            createBitmap.copyPixelsToBuffer(wrap);
            createBitmap.recycle();
            return bArr;
        } catch (OutOfMemoryError e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }
}
