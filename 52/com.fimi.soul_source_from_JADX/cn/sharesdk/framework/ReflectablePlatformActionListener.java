package cn.sharesdk.framework;

import android.os.Handler.Callback;
import android.os.Message;
import com.mob.tools.utils.UIHandler;
import java.util.HashMap;

public class ReflectablePlatformActionListener implements PlatformActionListener {
    private int f180a;
    private Callback f181b;
    private int f182c;
    private Callback f183d;
    private int f184e;
    private Callback f185f;

    public void onCancel(Platform platform, int i) {
        if (this.f185f != null) {
            Message message = new Message();
            message.what = this.f184e;
            message.obj = new Object[]{platform, Integer.valueOf(i)};
            UIHandler.sendMessage(message, this.f185f);
        }
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (this.f181b != null) {
            Message message = new Message();
            message.what = this.f180a;
            message.obj = new Object[]{platform, Integer.valueOf(i), hashMap};
            UIHandler.sendMessage(message, this.f181b);
        }
    }

    public void onError(Platform platform, int i, Throwable th) {
        if (this.f183d != null) {
            Message message = new Message();
            message.what = this.f182c;
            message.obj = new Object[]{platform, Integer.valueOf(i), th};
            UIHandler.sendMessage(message, this.f183d);
        }
    }

    public void setOnCancelCallback(int i, Callback callback) {
        this.f184e = i;
        this.f185f = callback;
    }

    public void setOnCompleteCallback(int i, Callback callback) {
        this.f180a = i;
        this.f181b = callback;
    }

    public void setOnErrorCallback(int i, Callback callback) {
        this.f182c = i;
        this.f183d = callback;
    }
}
