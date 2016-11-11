package p147m.framework.ui.widget.asyncview;

import java.io.FilterInputStream;
import java.io.InputStream;

/* renamed from: m.framework.ui.widget.asyncview.g */
class C2870g extends FilterInputStream {
    InputStream f14631a;

    protected C2870g(InputStream inputStream) {
        super(inputStream);
        this.f14631a = inputStream;
    }

    public long skip(long j) {
        long j2 = 0;
        while (j2 < j) {
            long skip = this.f14631a.skip(j - j2);
            if (skip == 0) {
                break;
            }
            j2 += skip;
        }
        return j2;
    }
}
