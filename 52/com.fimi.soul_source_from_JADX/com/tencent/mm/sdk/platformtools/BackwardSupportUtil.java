package com.tencent.mm.sdk.platformtools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ListView;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class BackwardSupportUtil {

    public class AnimationHelper {

        public interface IHelper {
            void cancelAnimation(View view, Animation animation);
        }

        public static void cancelAnimation(View view, Animation animation) {
            if (VERSION.SDK_INT >= 8) {
                new AnimationHelperImpl22().cancelAnimation(view, animation);
            } else {
                new AnimationHelperImpl21below().cancelAnimation(view, animation);
            }
        }

        public static void overridePendingTransition(Activity activity, int i, int i2) {
            activity.overridePendingTransition(i, i2);
        }
    }

    public class BitmapFactory {
        public static Bitmap decodeFile(String str, float f) {
            Options options = new Options();
            float f2 = 160.0f * f;
            options.inDensity = (int) f2;
            Bitmap decodeFile = android.graphics.BitmapFactory.decodeFile(str, options);
            if (decodeFile != null) {
                decodeFile.setDensity((int) f2);
            }
            return decodeFile;
        }

        public static Bitmap decodeStream(InputStream inputStream) {
            Options options = new Options();
            options.inDensity = SmileConstants.TOKEN_PREFIX_SHORT_UNICODE;
            options.inPreferredConfig = Config.ARGB_8888;
            return android.graphics.BitmapFactory.decodeStream(inputStream, null, options);
        }

        public static Bitmap decodeStream(InputStream inputStream, float f) {
            Options options = new Options();
            options.inDensity = (int) (160.0f * f);
            options.inPreferredConfig = Config.ARGB_8888;
            return android.graphics.BitmapFactory.decodeStream(inputStream, null, options);
        }

        public static int fromDPToPix(Context context, float f) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return Math.round((((float) displayMetrics.densityDpi) * f) / 160.0f);
        }

        public static Bitmap getBitmapFromURL(String str) {
            try {
                Log.m13539d("MicroMsg.SDK.BackwardSupportUtil", "get bitmap from url:" + str);
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                Bitmap decodeStream = decodeStream(inputStream);
                inputStream.close();
                return decodeStream;
            } catch (IOException e) {
                Log.m13541e("MicroMsg.SDK.BackwardSupportUtil", "get bitmap from url failed");
                e.printStackTrace();
                return null;
            }
        }

        public static String getDisplayDensityType(Context context) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            Configuration configuration = context.getResources().getConfiguration();
            String str = C2915a.f14760f;
            r0 = displayMetrics.density < C2020f.f10933c ? str + "LDPI" : displayMetrics.density >= 1.5f ? str + "HDPI" : str + "MDPI";
            return r0 + (configuration.orientation == 2 ? "_L" : "_P");
        }
    }

    public class ExifHelper {
        public static int getExifOrientation(String str) {
            ExifInterface exifInterface;
            try {
                exifInterface = new ExifInterface(str);
            } catch (IOException e) {
                Log.m13541e("MicroMsg.SDK.BackwardSupportUtil", "cannot read exif" + e);
                exifInterface = null;
            }
            if (exifInterface == null) {
                return 0;
            }
            int attributeInt = exifInterface.getAttributeInt("Orientation", -1);
            if (attributeInt == -1) {
                return 0;
            }
            switch (attributeInt) {
                case Type.BYTE /*3*/:
                    return Opcodes.GETFIELD;
                case Type.FLOAT /*6*/:
                    return 90;
                case Type.DOUBLE /*8*/:
                    return 270;
                default:
                    return 0;
            }
        }
    }

    public class SmoothScrollFactory {

        public interface IScroll {
            void doScroll(ListView listView);

            void doScroll(ListView listView, int i);
        }

        public static void scrollTo(ListView listView, int i) {
            if (VERSION.SDK_INT >= 8) {
                new SmoothScrollToPosition22().doScroll(listView, i);
            } else {
                new SmoothScrollToPosition21below().doScroll(listView, i);
            }
        }

        public static void scrollToTop(ListView listView) {
            if (VERSION.SDK_INT >= 8) {
                new SmoothScrollToPosition22().doScroll(listView);
            } else {
                new SmoothScrollToPosition21below().doScroll(listView);
            }
        }
    }
}
