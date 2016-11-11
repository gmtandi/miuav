package p000a;

import android.net.Uri;
import java.util.Collections;
import java.util.List;

/* renamed from: a.e */
public class C0006e {
    private Uri f46a;
    private List<C0007f> f47b;
    private Uri f48c;

    public C0006e(Uri uri, List<C0007f> list, Uri uri2) {
        List emptyList;
        this.f46a = uri;
        if (list == null) {
            emptyList = Collections.emptyList();
        }
        this.f47b = emptyList;
        this.f48c = uri2;
    }

    public Uri m32a() {
        return this.f46a;
    }

    public List<C0007f> m33b() {
        return Collections.unmodifiableList(this.f47b);
    }

    public Uri m34c() {
        return this.f48c;
    }
}
