package cn.sharesdk.framework.p013b.p015b;

import android.content.Context;

/* renamed from: cn.sharesdk.framework.b.b.c */
public abstract class C0171c {
    public long f233e;
    public String f234f;
    public String f235g;
    public String f236h;
    public int f237i;
    public String f238j;
    public int f239k;
    public String f240l;
    public String f241m;

    protected abstract String m524a();

    protected abstract void m525a(long j);

    public boolean m526a(Context context) {
        int b = m527b();
        int c = m529c();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - m531e() < ((long) b)) {
            return m530d() < ((long) c);
        } else {
            m525a(currentTimeMillis);
            return true;
        }
    }

    protected abstract int m527b();

    public void m528b(Context context) {
        m532f();
    }

    protected abstract int m529c();

    protected abstract long m530d();

    protected abstract long m531e();

    protected abstract void m532f();

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(m524a()).append(':');
        stringBuilder.append(this.f233e).append('|');
        stringBuilder.append(this.f234f).append('|');
        stringBuilder.append(this.f235g).append('|');
        stringBuilder.append(this.f236h).append('|');
        stringBuilder.append(this.f237i).append('|');
        stringBuilder.append(this.f238j).append('|');
        stringBuilder.append(this.f239k).append('|');
        stringBuilder.append(this.f240l);
        return stringBuilder.toString();
    }
}
