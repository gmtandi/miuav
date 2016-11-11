package com.mining.app.zxing.p126a;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.lang.reflect.Method;
import java.util.regex.Pattern;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.mining.app.zxing.a.b */
final class C2123b {
    private static final String f11150a;
    private static final int f11151b = 27;
    private static final int f11152c = 30;
    private static final Pattern f11153d;
    private final Context f11154e;
    private Point f11155f;
    private Point f11156g;
    private int f11157h;
    private String f11158i;

    static {
        f11150a = C2123b.class.getSimpleName();
        f11153d = Pattern.compile(MiPushClient.ACCEPT_TIME_SEPARATOR);
    }

    C2123b(Context context) {
        this.f11154e = context;
    }

    private static int m13058a(CharSequence charSequence, int i) {
        String[] split = f11153d.split(charSequence);
        int length = split.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            try {
                double parseDouble = Double.parseDouble(split[i2].trim());
                int i4 = (int) (10.0d * parseDouble);
                if (Math.abs(((double) i) - parseDouble) >= ((double) Math.abs(i - i3))) {
                    i4 = i3;
                }
                i2++;
                i3 = i4;
            } catch (NumberFormatException e) {
                return i;
            }
        }
        return i3;
    }

    private static Point m13059a(Parameters parameters, Point point) {
        String str = parameters.get("preview-size-values");
        if (str == null) {
            CharSequence charSequence = parameters.get("preview-size-value");
        } else {
            Object obj = str;
        }
        Point point2 = null;
        if (charSequence != null) {
            Log.d(f11150a, "preview-size-values parameter: " + charSequence);
            point2 = C2123b.m13060a(charSequence, point);
        }
        return point2 == null ? new Point((point.x >> 3) << 3, (point.y >> 3) << 3) : point2;
    }

    private static Point m13060a(CharSequence charSequence, Point point) {
        int indexOf;
        int parseInt;
        String[] split = f11153d.split(charSequence);
        int length = split.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = Integer.MAX_VALUE;
        while (i < length) {
            int i5;
            String trim = split[i].trim();
            indexOf = trim.indexOf(Opcodes.ISHL);
            if (indexOf < 0) {
                Log.w(f11150a, "Bad preview-size: " + trim);
                i5 = i2;
                i2 = i3;
            } else {
                try {
                    parseInt = Integer.parseInt(trim.substring(0, indexOf));
                    indexOf = Integer.parseInt(trim.substring(indexOf + 1));
                    i5 = Math.abs(parseInt - point.x) + Math.abs(indexOf - point.y);
                    if (i5 == 0) {
                        break;
                    } else if (i5 < i4) {
                        i4 = i5;
                        i2 = parseInt;
                        i5 = indexOf;
                    } else {
                        i5 = i2;
                        i2 = i3;
                    }
                } catch (NumberFormatException e) {
                    Log.w(f11150a, "Bad preview-size: " + trim);
                    i5 = i2;
                    i2 = i3;
                }
            }
            i++;
            i3 = i2;
            i2 = i5;
        }
        indexOf = i2;
        parseInt = i3;
        return (parseInt <= 0 || indexOf <= 0) ? null : new Point(parseInt, indexOf);
    }

    private void m13061a(Parameters parameters) {
        if (Build.MODEL.contains("Behold II") && C2124c.f11159a == 3) {
            parameters.set("flash-value", 1);
        } else {
            parameters.set("flash-value", 2);
        }
        parameters.set("flash-mode", "off");
    }

    private void m13062b(Parameters parameters) {
        String str = parameters.get("zoom-supported");
        if (str == null || Boolean.parseBoolean(str)) {
            int parseDouble;
            int i = f11151b;
            String str2 = parameters.get("max-zoom");
            if (str2 != null) {
                try {
                    parseDouble = (int) (Double.parseDouble(str2) * 10.0d);
                    if (f11151b <= parseDouble) {
                        parseDouble = f11151b;
                    }
                    i = parseDouble;
                } catch (NumberFormatException e) {
                    Log.w(f11150a, "Bad max-zoom: " + str2);
                }
            }
            String str3 = parameters.get("taking-picture-zoom-max");
            if (str3 != null) {
                try {
                    parseDouble = Integer.parseInt(str3);
                    if (i > parseDouble) {
                        i = parseDouble;
                    }
                } catch (NumberFormatException e2) {
                    Log.w(f11150a, "Bad taking-picture-zoom-max: " + str3);
                }
            }
            CharSequence charSequence = parameters.get("mot-zoom-values");
            if (charSequence != null) {
                i = C2123b.m13058a(charSequence, i);
            }
            String str4 = parameters.get("mot-zoom-step");
            if (str4 != null) {
                try {
                    int parseDouble2 = (int) (Double.parseDouble(str4.trim()) * 10.0d);
                    if (parseDouble2 > 1) {
                        i -= i % parseDouble2;
                    }
                } catch (NumberFormatException e3) {
                }
            }
            if (!(str2 == null && charSequence == null)) {
                parameters.set("zoom", String.valueOf(((double) i) / 10.0d));
            }
            if (str3 != null) {
                parameters.set("taking-picture-zoom", i);
            }
        }
    }

    public static int m13063e() {
        return f11152c;
    }

    Point m13064a() {
        return this.f11156g;
    }

    void m13065a(Camera camera) {
        Parameters parameters = camera.getParameters();
        this.f11157h = parameters.getPreviewFormat();
        this.f11158i = parameters.get("preview-format");
        Log.d(f11150a, "Default preview format: " + this.f11157h + '/' + this.f11158i);
        Display defaultDisplay = ((WindowManager) this.f11154e.getSystemService("window")).getDefaultDisplay();
        this.f11155f = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        Log.d(f11150a, "Screen resolution: " + this.f11155f);
        this.f11156g = C2123b.m13059a(parameters, this.f11155f);
        Log.d(f11150a, "Camera resolution: " + this.f11155f);
    }

    protected void m13066a(Camera camera, int i) {
        try {
            Method method = camera.getClass().getMethod("setDisplayOrientation", new Class[]{Integer.TYPE});
            if (method != null) {
                method.invoke(camera, new Object[]{Integer.valueOf(i)});
            }
        } catch (Exception e) {
        }
    }

    Point m13067b() {
        return this.f11155f;
    }

    void m13068b(Camera camera) {
        Parameters parameters = camera.getParameters();
        Log.d(f11150a, "Setting preview size: " + this.f11156g);
        parameters.setPreviewSize(this.f11156g.x, this.f11156g.y);
        m13061a(parameters);
        m13062b(parameters);
        m13066a(camera, 90);
        camera.setParameters(parameters);
    }

    int m13069c() {
        return this.f11157h;
    }

    String m13070d() {
        return this.f11158i;
    }
}
