package com.p054c.p055a.p063b.p068d;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Video.Thumbnails;
import android.webkit.MimeTypeMap;
import com.p054c.p055a.p063b.p064a.C0895a;
import com.p054c.p055a.p072c.C0957d;
import it.p074a.p075a.C2799f;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C3004e;

/* renamed from: com.c.a.b.d.a */
public class C0921a implements C0920c {
    public static final int f4832a = 5000;
    public static final int f4833b = 20000;
    protected static final int f4834c = 32768;
    protected static final String f4835d = "@#&=*+-_.,:!?()/~'%";
    protected static final int f4836e = 5;
    protected static final String f4837f = "content://com.android.contacts/";
    private static final String f4838j = "UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))";
    protected final Context f4839g;
    protected final int f4840h;
    protected final int f4841i;

    public C0921a(Context context) {
        this(context, f4832a, f4833b);
    }

    public C0921a(Context context, int i, int i2) {
        this.f4839g = context.getApplicationContext();
        this.f4840h = i;
        this.f4841i = i2;
    }

    @TargetApi(8)
    private InputStream m7254a(String str) {
        if (VERSION.SDK_INT >= 8) {
            Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, 2);
            if (createVideoThumbnail != null) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                createVideoThumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
        }
        return null;
    }

    private boolean m7255b(Uri uri) {
        String type = this.f4839g.getContentResolver().getType(uri);
        return type != null && type.startsWith("video/");
    }

    private boolean m7256b(String str) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
        return mimeTypeFromExtension != null && mimeTypeFromExtension.startsWith("video/");
    }

    @TargetApi(14)
    protected InputStream m7257a(Uri uri) {
        ContentResolver contentResolver = this.f4839g.getContentResolver();
        return VERSION.SDK_INT >= 14 ? Contacts.openContactPhotoInputStream(contentResolver, uri, true) : Contacts.openContactPhotoInputStream(contentResolver, uri);
    }

    public InputStream m7258a(String str, Object obj) {
        switch (C0922b.f4842a[C0923d.m7267a(str).ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return m7260b(str, obj);
            case Type.BYTE /*3*/:
                return m7262d(str, obj);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return m7263e(str, obj);
            case f4836e /*5*/:
                return m7264f(str, obj);
            case Type.FLOAT /*6*/:
                return m7265g(str, obj);
            default:
                return m7266h(str, obj);
        }
    }

    protected boolean m7259a(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getResponseCode() == C2799f.f14282t;
    }

    protected InputStream m7260b(String str, Object obj) {
        HttpURLConnection c = m7261c(str, obj);
        int i = 0;
        while (c.getResponseCode() / 100 == 3 && i < f4836e) {
            c = m7261c(c.getHeaderField(C3004e.f14996H), obj);
            i++;
        }
        try {
            Closeable inputStream = c.getInputStream();
            if (m7259a(c)) {
                return new C0895a(new BufferedInputStream(inputStream, f4834c), c.getContentLength());
            }
            C0957d.m7547a(inputStream);
            throw new IOException("Image request failed with response code " + c.getResponseCode());
        } catch (IOException e) {
            C0957d.m7548a(c.getErrorStream());
            throw e;
        }
    }

    protected HttpURLConnection m7261c(String str, Object obj) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, f4835d)).openConnection();
        httpURLConnection.setConnectTimeout(this.f4840h);
        httpURLConnection.setReadTimeout(this.f4841i);
        return httpURLConnection;
    }

    protected InputStream m7262d(String str, Object obj) {
        String c = C0923d.FILE.m7270c(str);
        return m7256b(str) ? m7254a(c) : new C0895a(new BufferedInputStream(new FileInputStream(c), f4834c), (int) new File(c).length());
    }

    protected InputStream m7263e(String str, Object obj) {
        ContentResolver contentResolver = this.f4839g.getContentResolver();
        Uri parse = Uri.parse(str);
        if (m7255b(parse)) {
            Bitmap thumbnail = Thumbnails.getThumbnail(contentResolver, Long.valueOf(parse.getLastPathSegment()).longValue(), 1, null);
            if (thumbnail != null) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
        } else if (str.startsWith(f4837f)) {
            return m7257a(parse);
        }
        return contentResolver.openInputStream(parse);
    }

    protected InputStream m7264f(String str, Object obj) {
        return this.f4839g.getAssets().open(C0923d.ASSETS.m7270c(str));
    }

    protected InputStream m7265g(String str, Object obj) {
        return this.f4839g.getResources().openRawResource(Integer.parseInt(C0923d.DRAWABLE.m7270c(str)));
    }

    protected InputStream m7266h(String str, Object obj) {
        throw new UnsupportedOperationException(String.format(f4838j, new Object[]{str}));
    }
}
