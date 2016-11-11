package p147m.framework.p149b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.text.TextUtils;
import com.baidu.tts.loopj.RequestParams;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Locale;

/* renamed from: m.framework.b.g */
public class C2863g {
    private static float f14603a;

    public static int m16519a(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int m16520a(Context context, int i) {
        if (f14603a <= 0.0f) {
            f14603a = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((((float) i) * f14603a) + 0.5f);
    }

    public static Bitmap m16521a(File file, int i) {
        InputStream fileInputStream = new FileInputStream(file);
        Bitmap a = C2863g.m16523a(fileInputStream, i);
        fileInputStream.close();
        return a;
    }

    public static Bitmap m16522a(InputStream inputStream) {
        return C2863g.m16523a(inputStream, 1);
    }

    private static Bitmap m16523a(InputStream inputStream, int i) {
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    public static Bitmap m16524a(String str, int i) {
        return C2863g.m16521a(new File(str), i);
    }

    public static String m16525a(Context context, String str) {
        String stringBuilder = new StringBuilder(String.valueOf(context.getFilesDir().getAbsolutePath())).append("/mFramework/cache/").toString();
        C2858b c2858b = new C2858b(context);
        if (c2858b.m16483u()) {
            stringBuilder = c2858b.m16484v() + "/mFramework/" + c2858b.m16477o() + "/cache/";
        }
        if (!TextUtils.isEmpty(str)) {
            stringBuilder = new StringBuilder(String.valueOf(stringBuilder)).append(str).append("/").toString();
        }
        File file = new File(stringBuilder);
        if (!file.exists()) {
            file.mkdir();
        }
        return stringBuilder;
    }

    public static boolean m16526a(String str) {
        return str == null || str.trim().length() <= 0 || "null".equals(str.trim().toLowerCase(Locale.getDefault()));
    }

    public static int m16527b(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static Bitmap m16528b(String str) {
        return C2863g.m16524a(str, 1);
    }

    public static String m16529c(String str) {
        Throwable th;
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str);
        if (contentTypeFor != null && contentTypeFor.length() > 0) {
            return contentTypeFor;
        }
        String toLowerCase = str.toLowerCase(Locale.getDefault());
        if (toLowerCase.endsWith("jpg") || toLowerCase.endsWith("jepg")) {
            return "image/jpeg";
        }
        if (toLowerCase.endsWith("png")) {
            return "image/png";
        }
        if (toLowerCase.endsWith("gif")) {
            return "image/gif";
        }
        try {
            InputStream fileInputStream = new FileInputStream(str);
            toLowerCase = URLConnection.guessContentTypeFromStream(fileInputStream);
            try {
                fileInputStream.close();
                contentTypeFor = toLowerCase;
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                contentTypeFor = toLowerCase;
                if (contentTypeFor != null) {
                }
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            toLowerCase = contentTypeFor;
            th = th4;
            th.printStackTrace();
            contentTypeFor = toLowerCase;
            if (contentTypeFor != null) {
            }
        }
        return (contentTypeFor != null || contentTypeFor.length() <= 0) ? RequestParams.APPLICATION_OCTET_STREAM : contentTypeFor;
    }
}
