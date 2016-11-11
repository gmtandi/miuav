package p147m.framework.p149b;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

/* renamed from: m.framework.b.e */
public class C2861e {
    private static Handler f14602a;

    public static void m16507a() {
        if (f14602a == null) {
            f14602a = new Handler(new C2862f());
        }
    }

    public static boolean m16509a(int i, long j, Callback callback) {
        return f14602a.sendMessageAtTime(C2861e.m16513b(i, callback), j);
    }

    public static boolean m16510a(int i, Callback callback) {
        return f14602a.sendMessage(C2861e.m16513b(i, callback));
    }

    public static boolean m16511a(Message message, long j, Callback callback) {
        return f14602a.sendMessageDelayed(C2861e.m16518c(message, callback), j);
    }

    public static boolean m16512a(Message message, Callback callback) {
        return f14602a.sendMessage(C2861e.m16518c(message, callback));
    }

    private static Message m16513b(int i, Callback callback) {
        Message message = new Message();
        message.what = i;
        return C2861e.m16518c(message, callback);
    }

    private static void m16514b(Message message) {
        Object[] objArr = (Object[]) message.obj;
        Message message2 = (Message) objArr[0];
        Callback callback = (Callback) objArr[1];
        if (callback != null) {
            callback.handleMessage(message2);
        }
    }

    public static boolean m16515b(int i, long j, Callback callback) {
        return f14602a.sendMessageDelayed(C2861e.m16513b(i, callback), j);
    }

    public static boolean m16516b(Message message, long j, Callback callback) {
        return f14602a.sendMessageAtTime(C2861e.m16518c(message, callback), j);
    }

    public static boolean m16517b(Message message, Callback callback) {
        return f14602a.sendMessageAtFrontOfQueue(C2861e.m16518c(message, callback));
    }

    private static Message m16518c(Message message, Callback callback) {
        Message message2 = new Message();
        message2.obj = new Object[]{message, callback};
        return message2;
    }
}
