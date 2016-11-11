package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.mipush.sdk.PushMessageHandler.C2563a;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class MessageHandleService extends IntentService {
    private static ConcurrentLinkedQueue<C2561a> f12858a;

    public interface ReceiverCallback {
        void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage);

        void onReceiveMessage(Context context, MiPushMessage miPushMessage);
    }

    /* renamed from: com.xiaomi.mipush.sdk.MessageHandleService.a */
    public class C2561a {
        private PushMessageReceiver f12856a;
        private Intent f12857b;

        public C2561a(Intent intent, PushMessageReceiver pushMessageReceiver) {
            this.f12856a = pushMessageReceiver;
            this.f12857b = intent;
        }

        public PushMessageReceiver m14593a() {
            return this.f12856a;
        }

        public Intent m14594b() {
            return this.f12857b;
        }
    }

    static {
        f12858a = new ConcurrentLinkedQueue();
    }

    public MessageHandleService() {
        super("MessageHandleThread");
    }

    public static void addJob(C2561a c2561a) {
        if (c2561a != null) {
            f12858a.add(c2561a);
        }
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            C2561a c2561a = (C2561a) f12858a.poll();
            if (c2561a != null) {
                try {
                    PushMessageReceiver a = c2561a.m14593a();
                    Intent b = c2561a.m14594b();
                    switch (b.getIntExtra(PushMessageHelper.MESSAGE_TYPE, 1)) {
                        case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                            C2563a a2 = C2571d.m14646a((Context) this).m14650a(b);
                            if (a2 == null) {
                                return;
                            }
                            if (a2 instanceof MiPushMessage) {
                                a.onReceiveMessage(this, (MiPushMessage) a2);
                                return;
                            } else if (a2 instanceof MiPushCommandMessage) {
                                a.onCommandResult(this, (MiPushCommandMessage) a2);
                                return;
                            } else {
                                return;
                            }
                        case Type.BYTE /*3*/:
                            a.onCommandResult(this, (MiPushCommandMessage) b.getSerializableExtra(PushMessageHelper.KEY_COMMAND));
                            return;
                        case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                            return;
                        default:
                            return;
                    }
                } catch (Throwable e) {
                    C2463b.m14125a(e);
                }
                C2463b.m14125a(e);
            }
        }
    }
}
